package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.yabuto.api.util.ObjectBuilder;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    public DeploymentTemplateContainerBuilder(final String name) {
        me().builderList("port", "ports", (args) -> new PortBuilder());
        me().simpleValue("name", "name", name, String.class);
        me().simpleValue("image", "image", null, String.class);
    }

    static DeploymentTemplateContainerBuilder create(final Object... args) {
        return new DeploymentTemplateContainerBuilder(string(args[0]));
    }

    private class PortBuilder extends ObjectBuilder<PortBuilder> {
        PortBuilder() {
            me().simpleValue("containerPort", "containerPort", null, int.class);
        }
    }
}
