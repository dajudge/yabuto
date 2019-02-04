package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.ymlgen.api.features.Builder;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

class DeploymentTemplateBuilder extends ObjectBuilder<DeploymentTemplateBuilder> {
    DeploymentTemplateBuilder() {
        me().keyValuePairs("label", "labels");
        me().child("spec")
                .builderList("container", "containers", this::createContainerBuilder);
    }

    private DeploymentTemplateContainerBuilder createContainerBuilder(final Object[] params) {
        return new DeploymentTemplateContainerBuilder(string(params[0]));
    }

    static Builder create(final Object[] params) {
        return new DeploymentTemplateBuilder();
    }
}
