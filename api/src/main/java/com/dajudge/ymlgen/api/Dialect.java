package com.dajudge.ymlgen.api;

import java.util.Map;

public interface Dialect {
    Map<String, Entrypoint> getEntrypoints(Project project);
}
