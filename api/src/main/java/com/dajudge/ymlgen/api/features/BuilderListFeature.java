package com.dajudge.ymlgen.api.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.dajudge.ymlgen.api.features.Builder.callBuilderClosure;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

class BuilderListFeature implements ApiFeature {
    private final String listName;
    private final Function<Object[], Builder> factory;
    private final boolean required;
    private final List<Map<String, Object>> results = new ArrayList<>();

    BuilderListFeature(final String listName, final Function<Object[], Builder> factory, final boolean required) {
        this.listName = listName;
        this.factory = factory;
        this.required = required;
    }

    @Override
    public void invoke(final Object[] args) {
        Builder builder = factory.apply(args);
        callBuilderClosure(args[args.length - 1], builder);
        results.add(builder.build());
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (!results.isEmpty() || required) {
            target.put(listName, results);
        }
    }
}
