package com.dajudge.yabuto.api.util;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.Entrypoint;

import java.util.function.Function;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final Function<String, ? extends ObjectBuilder<?>> builderFactory) {
        return p -> {
            final ObjectBuilder<?> builder = builderFactory.apply(from(p, 0));
            Builder.callBuilderClosure(from(p, 1), builder);
            return builder.build();
        };
    }

    private static <T> T from(final Object[] o, int index) {
        return (T) o[index];
    }
}
