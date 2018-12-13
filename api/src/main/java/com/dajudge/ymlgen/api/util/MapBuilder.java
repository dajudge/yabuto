package com.dajudge.ymlgen.api.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public MapBuilder<K, V> put(final K name, final V value) {
        map.put(name, value);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }
}
