package com.dajudge.yabuto.api.features;

import groovy.lang.Closure;

import java.util.Map;

import static groovy.lang.Closure.DELEGATE_FIRST;

public interface Builder {
    Map<String, Object> build();

    static void callBuilderClosure(final Object closureObject, final Builder builder) {
        if (!(closureObject instanceof Closure)) {
            return;
        }
        final Closure closure = (Closure) closureObject;
        closure.setDelegate(builder);
        closure.setResolveStrategy(DELEGATE_FIRST);
        closure.call();
    }
}
