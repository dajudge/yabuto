package com.dajudge.ymlgen.api.util;

import com.dajudge.ymlgen.api.Entrypoint;

import java.util.function.Function;

import static com.dajudge.ymlgen.api.features.Builder.callBuilderClosure;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final Function<String, ? extends ObjectBuilder<?>> builderFactory) {
        return p -> {
            final ObjectBuilder<?> builder = builderFactory.apply(from(p, 0));
            callBuilderClosure(from(p, 1), builder);
            return builder.build();
        };
    }

    private static <T> T from(final Object[] o, int index) {
        return (T) o[index];
    }
}
