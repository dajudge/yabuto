package com.dajudge.ymlgen.api;

import java.io.File;

public interface Project {
    File getTemplatesDir();

    ClassLoader getClassLoader();
}
