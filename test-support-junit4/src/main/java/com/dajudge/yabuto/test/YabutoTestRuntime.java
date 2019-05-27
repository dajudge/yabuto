package com.dajudge.yabuto.test;

import com.dajudge.yabuto.runtime.ScriptClasspath;
import com.dajudge.yabuto.runtime.SimpleEmitter;
import com.dajudge.yabuto.runtime.YabutoRuntime;
import com.dajudge.yabuto.test.yaml.YamlObject;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class YabutoTestRuntime implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
            }
        };
    }

    private File writeFile(final File targetDir, final String resource) {
        final File file = new File(targetDir, "test.groovy");
        try (
                final InputStream is = getResource(resource);
                final OutputStream os = new FileOutputStream(file)
        ) {
            final byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) > 0) {
                os.write(buffer, 0, read);
            }
            return file;
        } catch (final IOException e) {
            throw new RuntimeException("Failed to write temp file for resource " + resource, e);
        }
    }

    private InputStream getResource(final String resource) {
        final InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        if (is == null) {
            throw new RuntimeException("Could not find test script " + resource);
        }
        return is;
    }

    public void assertScript(
            final String input,
            final String output,
            final Map<String, String> params,
            final Consumer<YamlObject> assertions
    ) {
        final File tempDir = createTempDirectory();
        tempDir.deleteOnExit();
        final File templatesDir = new File(tempDir, "templates");
        final File targetDir = new File(tempDir, "target");
        if (!templatesDir.mkdirs()) {
            throw new RuntimeException("Could not create templates dir: " + templatesDir.getAbsolutePath());
        }
        if (!targetDir.mkdirs()) {
            throw new RuntimeException("Could not create target dir: " + targetDir.getAbsolutePath());
        }
        try {
            run(writeFile(targetDir, input), templatesDir, targetDir, params);
            assertResult(targetDir, output, assertions);
        } finally {
            try {
                Files.walkFileTree(tempDir.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (final IOException e) {
                System.err.println(e);
            }
        }
    }

    private File createTempDirectory() {
        try {
            return Files.createTempDirectory("yaubutotest_").toFile();
        } catch (final IOException e) {
            throw new RuntimeException("Failed to create temp directory for test", e);
        }
    }

    private void dump(final File targetDir, final String name, final PrintStream out) {
        final DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        emittedMap(targetDir, name, yaml -> out.println(new Yaml(options).dump(yaml)));
    }

    private void assertResult(final File targetDir, final String name, final Consumer<YamlObject> consumer) {
        emittedMap(targetDir, name, it -> {
            try {
                consumer.accept(new YamlObject(it));
            } catch (final RuntimeException | Error e) {
                dump(targetDir, name, System.err);
                throw e;
            }
        });
    }

    private void emittedMap(final File targetDir, final String name, final Consumer<Map<String, Object>> consumer) {
        try (final FileReader reader = new FileReader(new File(targetDir, name))) {
            consumer.accept(new Yaml().load(reader));
        } catch (final Exception e) {
            throw new RuntimeException("Failed to parse emitted yaml file: " + name, e);
        }
    }

    private void run(final File file, final File templatesDir, final File targetDir, final Map<String, String> params) {
        try {
            new YabutoRuntime(
                    getClasspath(),
                    templatesDir,
                    templatesDir,
                    new SimpleEmitter(targetDir)
            ).evaluate(file, params);
        } catch (final Exception e) {
            throw new RuntimeException("Failed to evaluate script " + file.getAbsolutePath(), e);
        }
    }

    private ScriptClasspath getClasspath() {
        final ClassLoader classloader = getClass().getClassLoader();
        final List<String> entries = asList(new String[]{});
        return new ScriptClasspath(entries, classloader);
    }
}
