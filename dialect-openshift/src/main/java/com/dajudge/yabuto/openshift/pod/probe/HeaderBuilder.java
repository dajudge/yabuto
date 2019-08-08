package com.dajudge.yabuto.openshift.pod.probe;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class HeaderBuilder extends ObjectBuilder<HeaderBuilder> {
    public HeaderBuilder() {
        me().simpleValue("name", "name", null, String.class)
                .simpleValue("value", "value", null, String.class);
    }

    public static Builder create(final Object[] objects) {
        return new HeaderBuilder();
    }
}
