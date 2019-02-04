package com.dajudge.ymlgen.api.features;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;
import static java.util.Arrays.asList;

public class KeyValueFeature implements ApiFeature {
    private final Map<String, Object> values = new HashMap<>();
    private final String featureName;

    public KeyValueFeature(final String featureName) {
        this.featureName = featureName;
    }

    @Override
    public void invoke(final Object[] args) {
        values.put(string(args[0]), args[1]);
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (!values.isEmpty()) {
            target.put(featureName, values);
        }
    }
}
