package com.dajudge.yabuto.runtime;

import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import static java.util.stream.StreamSupport.stream;

public class YabutoRuntime {
    private final ScriptClasspath classpath;
    private final File templatesDir;
    private final File rootDir;
    private final ServiceLoader<Dialect> dialectLoader;
    private final Emitter emitter;

    public interface Emitter {
        File emit(final String name, final String data);
    }

    public YabutoRuntime(
            final ScriptClasspath classpath,
            final File templatesDir,
            final File rootDir,
            final Emitter emitter
    ) {
        this.classpath = classpath;
        this.templatesDir = templatesDir;
        this.rootDir = rootDir;
        this.emitter = emitter;
        dialectLoader = apiModules(classpath.getClassLoader());
    }

    private ServiceLoader<Dialect> apiModules(final ClassLoader cl) {
        return ServiceLoader.load(Dialect.class, cl);
    }

    public void evaluate(final File file, final Map<String, String> parameters) throws Exception {
        final Project project = createProject(classpath.getClassLoader(), parameters);
        final Map<String, Entrypoint> apis = collectApiEndpoints(dialectLoader, project);
        YamlBuildScript.run(classpath, apis, file);
    }

    private Map<String, Entrypoint> collectApiEndpoints(
            final ServiceLoader<Dialect> apiModules,
            final Project project
    ) {
        final Map<String, Entrypoint> apis = new HashMap<>();
        stream(apiModules.spliterator(), false)
                .forEach(it -> apis.putAll(it.getEntrypoints(project)));
        return apis;
    }

    private Project createProject(final ClassLoader cl, final Map<String, String> parameters) {
        return new Project() {
            @Override
            public File getTemplatesDir() {
                return templatesDir;
            }

            @Override
            public File getRootDir() {
                return rootDir;
            }

            @Override
            public ClassLoader getClassLoader() {
                return cl;
            }

            @Override
            public Map<String, String> getParams() {
                return parameters;
            }

            @Override
            public File emit(String name, Map<String, Object> data) {
                return emitter.emit(name, toYamlString(data));
            }
        };
    }

    private String toYamlString(final Map<String, Object> data) {
        final DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(options).dump(data);
    }
}
