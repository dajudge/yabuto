package com.dajudge.yabuto.k8s.base;

import com.dajudge.yabuto.api.util.ObjectBuilder;

public class MetadataObjectBuilder<T extends MetadataObjectBuilder> extends ObjectBuilder<T> {
    public MetadataObjectBuilder(final String name) {
        me().child("metadata")
                .simpleValue("name", "name", name, String.class)
                .simpleValue("namespace", "namespace", null, String.class)
                .keyValuePairs("annotation", "annotations")
                .keyValuePairs("label", "labels");
    }

    public MetadataObjectBuilder() {
        this(null);
    }
}
