package com.dajudge.ymlgen.api.util;

import java.util.Map;

public class MetadataObjectBuilder<T extends MetadataObjectBuilder> extends ObjectBuilder<T> {

    public T name(final String name) {
        metadata().put("name", name);
        return me();
    }

    public T namespace(final String namespace) {
        metadata().put("namespace", namespace);
        return me();
    }

    public T annotation(final String key, final String value) {
        annotations().put(key, value);
        return me();
    }


    public MetadataObjectBuilder label(final String name, final String value) {
        labels().put(name, value);
        return this;
    }

    private Map<String, Object> annotations() {
        return submap(metadata(), "annotations");
    }

    private Map<String, Object> labels() {
        return submap(metadata(), "labels");
    }

    private Map<String, Object> metadata() {
        return submap(object, "metadata");
    }
}
