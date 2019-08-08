package com.dajudge.yabuto.api.util;

import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.features.Builder;

import java.util.function.Function;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public final class ClosureUtil {

    private ClosureUtil() {
    }

    public static Entrypoint root(final String name, final Function<String, ? extends ObjectBuilder<?>> builderFactory) {
        return p -> {
            if (p.length == 0) {
                throw new RuntimeException("DSL entrypoint '" + name + "' must have a name");
            }
            final ObjectBuilder<?> builder = builderFactory.apply(string(p[0]));
            if (p.length > 1) {
                Builder.callBuilderClosure(p[1], builder);
            }
            return builder.build();
        };
    }
}
