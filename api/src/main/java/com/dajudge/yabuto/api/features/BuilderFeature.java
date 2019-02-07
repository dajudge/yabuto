package com.dajudge.yabuto.api.features;

import com.dajudge.yabuto.api.exceptions.YabutoException;
import groovy.lang.Closure;

import java.util.Map;
import java.util.function.Function;

class BuilderFeature implements ApiFeature {
    private final String itemName;
    private final Function<Object[], Builder> factory;
    private Map<String, Object> result;

    BuilderFeature(final String itemName, final Function<Object[], Builder> factory) {
        this.itemName = itemName;
        this.factory = factory;
    }

    @Override
    public void invoke(final Object[] args) {
        try {
            final Builder builder = factory.apply(args);
            Builder.callBuilderClosure((Closure<?>) args[args.length - 1], builder);
            result = builder.build();
        } catch (final Exception e) {
            throw new YabutoException("Failed to invoke closure on '" + itemName + "'", e);
        }
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (result != null) {
            target.put(itemName, result);
        }
    }
}
