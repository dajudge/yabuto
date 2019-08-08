package com.dajudge.yabuto.openshift.security;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class ServiceAccountBuilder extends RootObjectBuilder<ServiceAccountBuilder> {
    public ServiceAccountBuilder(final String name) {
        super("ServiceAccount", "v1", name);
    }
}
