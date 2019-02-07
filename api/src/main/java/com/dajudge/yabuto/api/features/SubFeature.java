package com.dajudge.yabuto.api.features;

import java.util.HashMap;
import java.util.Map;

class SubFeature<F extends ApiFeature> implements ApiFeature {
    private final String path;
    private final F subFeature;

    SubFeature(final String path, final F subFeature) {
        this.path = path;
        this.subFeature = subFeature;
    }

    @Override
    public void invoke(final Object[] args) {
        subFeature.invoke(args);
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (target.containsKey(path)) {
            subFeature.build((Map<String, Object>) target.get(path));
        } else {
            final HashMap<String, Object> newMap = new HashMap<>();
            subFeature.build(newMap);
            if (!newMap.isEmpty()) {
                target.put(path, newMap);
            }
        }

    }
}
