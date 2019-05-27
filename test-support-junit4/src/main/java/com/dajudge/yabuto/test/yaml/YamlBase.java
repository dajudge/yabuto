package com.dajudge.yabuto.test.yaml;

import java.util.List;

import static java.util.Arrays.asList;

class YamlBase {
    protected static Object safeCast(final Object o, final Class<?>... classes) {
        final List<Class<?>> types = asList(classes);
        if (0 == types.stream().filter(it -> it.isAssignableFrom(o.getClass())).count()) {
            throw new AssertionError("Value is not of type " + types + ": " + o);
        }
        return o;
    }
}
