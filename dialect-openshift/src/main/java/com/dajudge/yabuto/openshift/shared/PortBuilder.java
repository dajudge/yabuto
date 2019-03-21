package com.dajudge.yabuto.openshift.shared;

import com.dajudge.yabuto.api.util.ObjectBuilder;

public class PortBuilder extends ObjectBuilder<PortBuilder> {
    public PortBuilder() {
        me()
                .simpleValue("name", "name", null, String.class)
                .simpleValue("port", "port", null, int.class)
                .simpleValue("containerPort", "containerPort", null, int.class)
                .simpleValue("targetPort", "targetPort", null, int.class);

    }
}
