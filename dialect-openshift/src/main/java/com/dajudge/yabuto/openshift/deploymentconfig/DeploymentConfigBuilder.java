package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.k8s.deployment.RecreateDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.deployment.RollingDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.api.exceptions.YabutoException;
import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.features.FeatureOwner;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class DeploymentConfigBuilder extends RootObjectBuilder<DeploymentConfigBuilder> {
    public DeploymentConfigBuilder(final String name) {
        super("DeploymentConfig", "v1", name);
        final FeatureOwner spec = me().child("spec");
        spec.simpleValue("replicas", "replicas", null, int.class)
                .keyValuePairs("selector", "selector")
                .requiredBuilderList("trigger", "triggers", this::triggerBuilder)
                .builder("template", "template", DeploymentTemplateBuilder::create)
                .builder("rollingStrategy", "strategy", RollingDeploymentStrategyBuilder::create)
                .builder("recreateStrategy", "strategy", RecreateDeploymentStrategyBuilder::create);
    }

    private Builder triggerBuilder(final Object[] params) {
        final String type = string(params[0]);
        switch (type) {
            case "ImageChange":
                return new ImageChangeTriggerBuilder();
            case "ConfigChange":
                return new ConfigChangeTriggerBuilder();
            default:
                throw new YabutoException("Unknown deployment trigger type: " + type);
        }
    }
}
