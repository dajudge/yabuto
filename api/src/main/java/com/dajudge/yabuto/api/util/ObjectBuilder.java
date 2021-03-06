package com.dajudge.yabuto.api.util;

import com.dajudge.yabuto.api.features.ApiFeature;
import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.features.FeatureOwner;
import groovy.lang.GroovyObjectSupport;

import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder<T extends ObjectBuilder> extends GroovyObjectSupport implements Builder {
    private Map<String, ApiFeature> features = new HashMap<>();

    protected FeatureOwner me() {
        return new FeatureOwner((featureName, feature) -> features.put(featureName, feature));
    }

    @Override
    public Object invokeMethod(final String name, final Object args) {
        if (features.containsKey(name)) {
            features.get(name).invoke((Object[]) args);
            return null;
        }
        return super.invokeMethod(name, args);
    }

    @Override
    public Map<String, Object> build() {
        final Map<String, Object> ret = new HashMap<>();
        features.values().forEach(it -> it.build(ret));
        return ret;
    }
}
