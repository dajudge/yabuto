package com.dajudge.yabuto.api;

import java.io.File;
import java.util.Map;
import java.util.function.Function;

public interface Project {
    File getTemplatesDir();

    ClassLoader getClassLoader();

    Map<String, String> getParams();

    File emit(String name, Map<String, Object> data);
}
