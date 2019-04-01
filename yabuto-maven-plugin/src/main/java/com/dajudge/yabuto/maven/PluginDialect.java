package com.dajudge.yabuto.maven;

import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import com.dajudge.yabuto.api.util.MapBuilder;

import java.io.File;
import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;
import static com.dajudge.yabuto.api.util.StreamUtil.loadBytes;
import static com.dajudge.yabuto.api.util.StreamUtil.loadUtf8FromResource;

public class PluginDialect implements Dialect {
    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put("templatesDir", p -> project.getTemplatesDir())
                .put("rootDir", p -> project.getRootDir())
                .put("file", params -> file(project, params))
                .put("classpath", classpath(project.getClassLoader()))
                .put("params", params(project.getParams()))
                .put("emit", emit(project))
                .build();
    }

    private Entrypoint emit(final Project project) {
        return p -> project.emit(string(p[0]), (Map<String, Object>)p[1]);
    }

    private Entrypoint params(final Map<String, String> params) {
        return p -> params.get(string(p[0]));
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

    private File file(Project project, final Object[] params) {
        return new File(project.getTemplatesDir(), string(params[0]));
    }
}
