package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    public DeploymentTemplateContainerBuilder(final String name) {
        object.put("name", name);
    }

    public DeploymentTemplateContainerBuilder image(String image) {
        object.put("image", image);
        return me();
    }
}
