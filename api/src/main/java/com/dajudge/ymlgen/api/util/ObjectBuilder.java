package com.dajudge.ymlgen.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectBuilder<T extends ObjectBuilder> {

    protected Map<String, Object> object = new HashMap<>();

    protected <T> T me() {
        return (T) this;
    }

    protected Map<String, Object> submap(final Map<String, Object> map, final String key) {
        if (!map.containsKey(key)) {
            map.put(key, new HashMap<String, Object>());
        }
        return (Map<String, Object>) map.get(key);
    }

    protected List<Object> sublist(final Map<String, Object> map, final String key) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        return (List<Object>) map.get(key);
    }

    public Map<String, Object> build() {
        return object;
    }
}
