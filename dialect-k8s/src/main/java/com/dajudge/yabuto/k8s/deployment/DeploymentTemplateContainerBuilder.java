package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    public DeploymentTemplateContainerBuilder(final String name) {
        me().simpleList("containerPort", "ports", "containerPort", Integer.class);
        me().simpleValue("name", "name", name, String.class);
        me().simpleValue("image", "image", null, String.class);
    }

    static DeploymentTemplateContainerBuilder create(final Object... args) {
        return new DeploymentTemplateContainerBuilder((String) args[0]);
    }
}
