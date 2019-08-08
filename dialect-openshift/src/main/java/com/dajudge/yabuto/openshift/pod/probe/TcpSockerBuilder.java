package com.dajudge.yabuto.openshift.pod.probe;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class TcpSockerBuilder extends ObjectBuilder<TcpSockerBuilder> {
    public static Builder create(final Object[] objects) {
        return new TcpSockerBuilder();
    }
}
