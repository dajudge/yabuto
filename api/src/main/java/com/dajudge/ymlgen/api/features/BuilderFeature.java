package com.dajudge.ymlgen.api.features;

import groovy.lang.Closure;

import java.util.Map;
import java.util.function.Function;

import static com.dajudge.ymlgen.api.features.Builder.callBuilderClosure;

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
        result = callBuilderClosure((Closure<?>) args[args.length-1], factory.apply(args));
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (result != null) {
            target.put(itemName, result);
        }
    }
}
