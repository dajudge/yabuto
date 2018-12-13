package com.dajudge.ymlgen.api.features;

import com.dajudge.ymlgen.api.util.ObjectBuilder;
import groovy.lang.Closure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;

class BuilderListFeature implements ApiFeature {
    private final String listName;
    private final Function<Object[], ObjectBuilder<?>> factory;
    private List<Map<String, Object>> results = new ArrayList<>();

    BuilderListFeature(final String listName, final Function<Object[], ObjectBuilder<?>> factory) {
        this.listName = listName;
        this.factory = factory;
    }

    @Override
    public void invoke(final Object[] args) {
        results.add(callBuilderClosure((Closure<?>) args[args.length-1], factory.apply(args)));
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (!results.isEmpty()) {
            target.put(listName, results);
        }
    }
}
