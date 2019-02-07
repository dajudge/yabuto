package com.dajudge.yabuto.maven;

import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.apache.maven.plugins.annotations.ResolutionScope.COMPILE;

@Mojo(
        name = "generate",
        requiresDependencyResolution = COMPILE,
        requiresDependencyCollection = COMPILE
)
public class GenerateMojo extends AbstractMojo {
    private static final String EXT = ".groovy";

    @Component
    MavenProject mavenProject;
    @Parameter(required = true)
    File templatesDir;
    @Parameter(required = true)
    File targetDir;
    @Parameter
    Map<String, String> parameters;

    public void execute() throws MojoExecutionException {
        final ClassLoader cl = createClassLoader();
        final ServiceLoader<Dialect> apiModules = apiModules(cl);
        final List<File> files = Stream.of(templatesDir.listFiles())
                .filter(File::isFile)
                .filter(it -> it.getName().endsWith(EXT))
                .collect(toList());
        targetDir.mkdirs();
        for (final File file : files) {
            final String sourceName = file.getName();
            final String templateName = sourceName.substring(0, sourceName.length() - EXT.length());
            final String targetName = templateName + ".yml";
            try (
                    final FileOutputStream fos = new FileOutputStream(new File(targetDir, targetName));
                    final OutputStreamWriter writer = new OutputStreamWriter(fos);
                    final PrintWriter print = new PrintWriter(writer);
            ) {
                final Project project = createProject(cl);
                final Map<String, Entrypoint> apis = collectApiEndpoints(apiModules, project);
                evaluate(cl, file, print, apis);
            } catch (final IOException e) {
                throw new MojoExecutionException("Failed to evaluate template.", e);
            }
        }
    }

    private Project createProject(final ClassLoader cl) {
        return new Project() {
            @Override
            public File getTemplatesDir() {
                return templatesDir;
            }

            @Override
            public ClassLoader getClassLoader() {
                return cl;
            }

            @Override
            public Map<String, String> getParams() {
                return parameters == null ? emptyMap() : new HashMap<>(parameters);
            }
        };
    }

    private ClassLoader createClassLoader() throws MojoExecutionException {
        try {
            final List<URL> urls = mavenProject.getCompileClasspathElements().stream()
                    .map(this::toURL)
                    .collect(toList());
            return new URLClassLoader(
                    urls.toArray(new URL[urls.size()]),
                    getClass().getClassLoader()
            );
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException("Failed to build script classpath.", e);
        }
    }

    private Map<String, Entrypoint> collectApiEndpoints(ServiceLoader<Dialect> apiModules, Project project) {
        final Map<String, Entrypoint> apis = new HashMap<>();
        stream(apiModules.spliterator(), false)
                .forEach(it -> apis.putAll(it.getEntrypoints(project)));
        return apis;
    }

    private void evaluate(
            final ClassLoader cl,
            final File script,
            final PrintWriter out,
            final Map<String, Entrypoint> apis
    ) throws MojoExecutionException {
        try {
            final DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            final Map<String, Object> yaml = YamlBuildScript.run(cl, apis, script);
            new Yaml(options).dump(yaml, out);
        } catch (final Exception e) {
            throw new MojoExecutionException("Failed to evaluate " + script.getPath(), e);
        }
    }

    private ServiceLoader<Dialect> apiModules(final ClassLoader cl) throws MojoExecutionException {
        try {
            return ServiceLoader.load(Dialect.class, cl);
        } catch (final Exception e) {
            throw new MojoExecutionException("Failed to collect API modules.", e);
        }
    }

    private URL toURL(final String path) {
        try {
            return new File(path).toURI().toURL();
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Failed to convert classpath entry to URL.", e);
        }
    }
}
