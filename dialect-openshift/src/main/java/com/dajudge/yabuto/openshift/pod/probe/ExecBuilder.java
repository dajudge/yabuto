package com.dajudge.yabuto.openshift.pod.probe;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

import java.util.List;

public class ExecBuilder extends ObjectBuilder<ExecBuilder> {
    public ExecBuilder() {
        me().simpleValue("command", "command", null, List.class);
    }

    public static Builder create(final Object[] objects) {
        return new ExecBuilder();
    }
}
