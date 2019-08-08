package com.dajudge.yabuto.openshift.security;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class RuleBuilder extends ObjectBuilder<RuleBuilder> {
    public RuleBuilder() {
        me().simpleList("apiGroup", "apiGroups", String.class)
                .simpleList("resource", "resources", String.class)
                .simpleList("verb", "verbs", String.class)
                .simpleList("resourceName", "resourceNames", String.class);
    }

    public static Builder create(final Object[] objects) {
        return new RuleBuilder();
    }
}
