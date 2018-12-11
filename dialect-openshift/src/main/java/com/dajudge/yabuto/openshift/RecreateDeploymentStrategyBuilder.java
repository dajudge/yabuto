package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class RecreateDeploymentStrategyBuilder extends ObjectBuilder<RecreateDeploymentStrategyBuilder> {
    public RecreateDeploymentStrategyBuilder() {
        object.put("type", "Recreate");
    }
}
