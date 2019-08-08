package com.dajudge.yabuto.openshift.security;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class ObjectReferenceBuilder extends ObjectBuilder<ObjectReferenceBuilder> {
    public ObjectReferenceBuilder() {
        me().simpleValue("apiGroup", "apiGroup", null, String.class)
                .simpleValue("kind", "kind", null, String.class)
                .simpleValue("name", "name", null, String.class)
                .simpleValue("namespace", "namespace", null, String.class);
    }

    public static Builder create(final Object[] objects) {
        return new ObjectReferenceBuilder();
    }
}
