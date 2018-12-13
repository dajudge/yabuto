package com.dajudge.ymlgen.api.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

class SimpleListFeature<T> implements ApiFeature {
    private final String name;
    private final String itemName;
    private List<T> items = new ArrayList<>();

    SimpleListFeature(final String name, final String itemName) {
        this.name = name;
        this.itemName = itemName;
    }

    @Override
    public final void invoke(final Object[] args) {
        items.add((T) args[0]);
    }

    @Override
    public final void build(final Map<String, Object> target) {
        if(items.isEmpty()) {
            return;
        }
        final List<Map<String, Object>> result = items.stream()
                .map(this::valueMapper)
                .collect(toList());
        target.put(name, result);
    }

    private Map<String, Object> valueMapper(final T it) {
        return new HashMap<String, Object>() {{
            put(itemName, it);
        }};
    }
}
