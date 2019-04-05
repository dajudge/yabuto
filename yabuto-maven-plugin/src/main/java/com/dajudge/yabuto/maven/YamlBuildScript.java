package com.dajudge.yabuto.maven;

import com.dajudge.yabuto.api.Entrypoint;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.dajudge.yabuto.api.util.StreamUtil.loadUtf8FromResource;

public abstract class YamlBuildScript extends Script {
    private Map<String, Entrypoint> apis;
    private ScriptClasspath scriptClasspath;

    @Override
    public Object invokeMethod(String name, Object args) {
        if (name.equals("include")) {
            final String scriptName = ((Object[]) args)[0].toString() + ".groovy";
            try {
                return run(scriptClasspath, apis, parserFor(scriptName));
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
        return loadUtf8FromResource(file, scriptClasspath.getClassLoader());
    }

    private interface Parser {
        Script parse(GroovyShell shell) throws IOException;
    }

    static Object run(
            final ScriptClasspath scriptClasspath,
            final Map<String, Entrypoint> apis,
            final File file
    ) throws IOException {
        return run(scriptClasspath, apis, shell -> shell.parse(file));
    }

    private static Object run(
            final ScriptClasspath scriptClasspath,
            final Map<String, Entrypoint> apis,
            final Parser parser
    ) throws IOException {
        final CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(YamlBuildScript.class.getName());
        config.setClasspathList(scriptClasspath.getEntries());
        final GroovyShell shell = new GroovyShell(new Binding(), config);
        final YamlBuildScript script = (YamlBuildScript) parser.parse(shell);
        script.scriptClasspath = scriptClasspath;
        script.apis = apis;
        return script.run();
    }
}
