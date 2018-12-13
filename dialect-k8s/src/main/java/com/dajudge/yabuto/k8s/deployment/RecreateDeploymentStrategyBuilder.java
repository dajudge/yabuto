package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class RecreateDeploymentStrategyBuilder extends ObjectBuilder<RecreateDeploymentStrategyBuilder> {
    private RecreateDeploymentStrategyBuilder() {
        me().simpleValue("type", "type", "Recreate", String.class);
    }

    public static RecreateDeploymentStrategyBuilder create(final Object[] objects) {
        return new RecreateDeploymentStrategyBuilder();
    }
}
