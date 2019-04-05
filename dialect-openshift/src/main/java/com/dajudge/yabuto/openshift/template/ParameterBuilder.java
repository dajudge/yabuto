package com.dajudge.yabuto.openshift.template;

import com.dajudge.yabuto.api.util.ObjectBuilder;

public class ParameterBuilder extends ObjectBuilder<ParameterBuilder> {
    ParameterBuilder() {
        me().simpleValue("name", "name", null, String.class)
                .simpleValue("value", "value", null, String.class)
                .simpleValue("description", "description", null, String.class)
                .simpleValue("required", "required", null, Boolean.class);

    }

    public static ParameterBuilder create(final Object[] params) {
        return new ParameterBuilder();
    }
}
