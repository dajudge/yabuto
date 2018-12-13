package com.dajudge.ymlgen.api.util;

import com.dajudge.ymlgen.api.Entrypoint;
import groovy.lang.Closure;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.dajudge.ymlgen.api.features.Builder.callBuilderClosure;
import static groovy.lang.Closure.DELEGATE_FIRST;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final Function<String, ? extends ObjectBuilder<?>> builder) {
        return p -> callBuilderClosure(from(p, 1), builder.apply(from(p, 0)));
    }

    private static <T> T from(final Object[] o, int index) {
        return (T) o[index];
    }
}
