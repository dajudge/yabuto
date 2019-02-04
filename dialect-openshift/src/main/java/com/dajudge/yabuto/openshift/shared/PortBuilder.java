package com.dajudge.yabuto.openshift.shared;

import com.dajudge.yabuto.openshift.service.ServiceBuilder;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class PortBuilder extends ObjectBuilder<PortBuilder> {
    public PortBuilder() {
        me()
                .simpleValue("port", "port", null, int.class)
                .simpleValue("containerPort", "containerPort", null, int.class)
                .simpleValue("targetPort", "targetPort", null, int.class);

    }
}
