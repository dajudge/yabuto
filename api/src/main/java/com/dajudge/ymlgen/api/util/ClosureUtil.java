package com.dajudge.ymlgen.api.util;

import com.dajudge.ymlgen.api.Entrypoint;
import groovy.lang.Closure;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static groovy.lang.Closure.DELEGATE_FIRST;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final Function<String, ? extends ObjectBuilder<?>> builder) {
        return p -> callBuilderClosure(from(p, 1), builder.apply(from(p, 0)));
    }

    public static Map<String, Object> callBuilderClosure(final Closure<?> closure, final ObjectBuilder<?> builder) {
        closure.setDelegate(builder);
        closure.setResolveStrategy(DELEGATE_FIRST);
        closure.call();
        return builder.build();
    }

    private static <T> T from(final Object[] o, int index) {
        return (T) o[index];
    }
}
