package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class DeploymentBuilder extends RootObjectBuilder<DeploymentBuilder> {
    public DeploymentBuilder(final String name) {
        super("Deployment", "apps/v1", name);
        me().child("spec")
                .builder("template", "template", args -> new DeploymentTemplateBuilder())
                .keyValuePairs("matchLabel", "matchLabels")
                .simpleValue("replicas", "replicas", null, Integer.class)
                .builder("rollingStrategy", "strategy", RollingDeploymentStrategyBuilder::create)
                .builder("recreateStrategy", "strategy", RecreateDeploymentStrategyBuilder::create);
    }
}
