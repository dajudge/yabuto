package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.k8s.deployment.RecreateDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.deployment.RollingDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;
import com.dajudge.ymlgen.api.exceptions.YabutoException;
import com.dajudge.ymlgen.api.features.Builder;
import com.dajudge.ymlgen.api.features.FeatureOwner;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

public class DeploymentConfigBuilder extends RootObjectBuilder<DeploymentConfigBuilder> {
    public DeploymentConfigBuilder(final String name) {
        super("DeploymentConfig", "v1", name);
        final FeatureOwner spec = me().child("spec");
        spec  .simpleValue("replicas", "replicas", null, int.class)
                .builderList("trigger", "triggers", this::triggerBuilder)
                .builder("template", "template", DeploymentTemplateBuilder::create)
                .builder("rollingStrategy", "strategy", RollingDeploymentStrategyBuilder::create)
                .builder("recreateStrategy", "strategy", RecreateDeploymentStrategyBuilder::create);
        spec.child("from")
                .custom("fromImageStreamTag", new ImageStreamTagFeature());
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
