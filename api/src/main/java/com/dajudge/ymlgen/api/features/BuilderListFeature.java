package com.dajudge.ymlgen.api.features;

import groovy.lang.Closure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.dajudge.ymlgen.api.features.Builder.callBuilderClosure;

class BuilderListFeature implements ApiFeature {
    private final String listName;
    private final Function<Object[], Builder> factory;
    private List<Map<String, Object>> results = new ArrayList<>();

    BuilderListFeature(final String listName, final Function<Object[], Builder> factory) {
        this.listName = listName;
        this.factory = factory;
    }

    @Override
    public void invoke(final Object[] args) {
        Builder builder = factory.apply(args);
        callBuilderClosure(args[args.length-1], builder);
        results.add(builder.build());
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (!results.isEmpty()) {
            target.put(listName, results);
        }
    }
}
