package com.dajudge.ymlgen.api.features;

import java.util.HashMap;
import java.util.Map;

public class KeyValueFeature implements ApiFeature {
    private static final Map<String, Object> values = new HashMap<>();
    private final String featureName;

    public KeyValueFeature(final String featureName) {
        this.featureName = featureName;
    }

    @Override
    public void invoke(final Object[] args) {
        values.put((String) args[0], args[1]);
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (!values.isEmpty()) {
            target.put(featureName, values);
        }
    }
}
