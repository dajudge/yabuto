package com.dajudge.ymlgen.maven;

import com.dajudge.ymlgen.api.Dialect;
import com.dajudge.ymlgen.api.Entrypoint;
import com.dajudge.ymlgen.api.Project;
import com.dajudge.ymlgen.api.util.MapBuilder;

import java.io.File;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.StreamUtil.loadBytes;
import static com.dajudge.ymlgen.api.util.StreamUtil.loadUtf8FromResource;

public class PluginDialect implements Dialect {
    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put("templatesDir", p -> project.getTemplatesDir())
                .put("file", PluginDialect.this::file)
                .put("classpath", classpath(project.getClassLoader()))
                .build();
    }

    private Entrypoint classpath(final ClassLoader cl) {
        return p -> new BinaryResource() {
            public String text() {
                return loadUtf8FromResource(p[0].toString(), cl);
            }

            public byte[] binary() {
                return loadBytes(p[0].toString(), () -> cl.getResourceAsStream(p[0].toString()));
            }
        };
    }

    private File file(final Object[] params) {
        return new File((File) params[0], (String) params[1]);
    }
}
