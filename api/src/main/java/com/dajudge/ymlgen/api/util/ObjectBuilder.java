package com.dajudge.ymlgen.api.util;

import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.features.FeatureOwner;
import groovy.lang.GroovyObjectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectBuilder<T extends ObjectBuilder> extends GroovyObjectSupport {
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

    public Map<String, Object> build() {
        final Map<String, Object> ret = new HashMap<>();
        features.values().forEach(it -> it.build(ret));
        return ret;
    }
}
