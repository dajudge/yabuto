package com.dajudge.yabuto.api.util;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.Entrypoint;

import java.util.function.Function;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final Function<String, ? extends ObjectBuilder<?>> builderFactory) {
        return p -> {
            final ObjectBuilder<?> builder = builderFactory.apply(string(p[0]));
            Builder.callBuilderClosure(p[1], builder);
            return builder.build();
        };
    }
}
