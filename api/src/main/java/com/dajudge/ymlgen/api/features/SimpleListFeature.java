package com.dajudge.ymlgen.api.features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SimpleListFeature<T> implements ApiFeature {
    private final String name;
    private List<T> items = new ArrayList<>();

    SimpleListFeature(final String name) {
        this.name = name;
    }

    @Override
    public final void invoke(final Object[] args) {
        items.add((T) args[0]);
    }

    @Override
    public final void build(final Map<String, Object> target) {
        if (items.isEmpty()) {
            return;
        }
        target.put(name, items);
    }
}
