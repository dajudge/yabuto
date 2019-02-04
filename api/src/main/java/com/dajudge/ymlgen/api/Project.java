package com.dajudge.ymlgen.api;

import java.io.File;
import java.util.Map;
import java.util.function.Function;

public interface Project {
    File getTemplatesDir();

    ClassLoader getClassLoader();

    Map<String, String> getParams();
}
