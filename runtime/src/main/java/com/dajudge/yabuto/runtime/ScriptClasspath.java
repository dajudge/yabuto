package com.dajudge.yabuto.runtime;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ScriptClasspath {
    private final List<String> entries;
    private final ClassLoader classLoader;

    public ScriptClasspath(final List<String> entries, final ClassLoader classLoader) {
        this.entries = unmodifiableList(entries);
        this.classLoader = classLoader;
    }

    public List<String> getEntries() {
        return entries;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }
}
