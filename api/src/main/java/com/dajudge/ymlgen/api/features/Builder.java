package com.dajudge.ymlgen.api.features;

import groovy.lang.Closure;

import java.util.Map;

import static groovy.lang.Closure.DELEGATE_FIRST;

public interface Builder {
    Map<String, Object> build();

    static Map<String, Object> callBuilderClosure(final Closure<?> closure, final Builder builder) {
        closure.setDelegate(builder);
        closure.setResolveStrategy(DELEGATE_FIRST);
        closure.call();
        return builder.build();
    }
}
