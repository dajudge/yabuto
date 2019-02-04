package com.dajudge.ymlgen.maven;

import com.dajudge.ymlgen.api.Entrypoint;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.StreamUtil.loadUtf8FromResource;

public abstract class YamlBuildScript extends Script {
    private Map<String, Entrypoint> apis;
    private ClassLoader scriptClassLoader;

    @Override
    public Object invokeMethod(String name, Object args) {
        if (name.equals("include")) {
            final String scriptName = ((Object[]) args)[0].toString() + ".groovy";
            try {
                return run(scriptClassLoader, apis, parserFor(scriptName));
            } catch (IOException e) {
                throw new RuntimeException("Failed to evaluate library " + scriptName, e);
            }
        }
        if (apis.containsKey(name)) {
            return apis.get(name).enter((Object[]) args);
        } else {
            return super.invokeMethod(name, args);
        }
    }

    private Parser parserFor(final String scriptName) {
        return shell -> shell.parse(load(scriptName), scriptName);
    }

    private String load(final String file) {
        return loadUtf8FromResource(file, scriptClassLoader);
    }

    private interface Parser {
        Script parse(GroovyShell shell) throws IOException;
    }

    static Map<String, Object> run(
            final ClassLoader classLoader,
            final Map<String, Entrypoint> apis,
            final File file
    ) throws IOException {
        return (Map<String, Object>) run(classLoader, apis, shell -> shell.parse(file));
    }

    private static Object run(
            final ClassLoader classLoader,
            final Map<String, Entrypoint> apis,
            final Parser parser
    ) throws IOException {
        final CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(YamlBuildScript.class.getName());
        final GroovyShell shell = new GroovyShell(classLoader, new Binding(), config);
        final YamlBuildScript script = (YamlBuildScript) parser.parse(shell);
        script.scriptClassLoader = classLoader;
        script.apis = apis;
        return script.run();
    }
}
