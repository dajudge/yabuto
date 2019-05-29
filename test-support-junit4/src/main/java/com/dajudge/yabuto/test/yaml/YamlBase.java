package com.dajudge.yabuto.test.yaml;

import java.util.List;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

class YamlBase {
    protected static <T> T withFallback(final boolean required, final Supplier<T> supplier, final Supplier<T> fallback) {
        try {
            return supplier.get();
        } catch (final AssertionError e) {
            if (required) {
                throw e;
            }
            return fallback.get();
        }
    }

    protected static Object safeCast(final Object o, final Class<?>... classes) {
        final List<Class<?>> types = asList(classes);
        if (0 == types.stream().filter(it -> it.isAssignableFrom(o.getClass())).count()) {
            throw new AssertionError("Value is not of type " + types + ": " + o);
        }
        return o;
    }
}
