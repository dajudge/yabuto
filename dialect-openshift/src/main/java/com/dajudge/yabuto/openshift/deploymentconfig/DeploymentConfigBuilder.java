package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.k8s.deployment.RecreateDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.deployment.RollingDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class DeploymentConfigBuilder extends RootObjectBuilder<DeploymentConfigBuilder> {
    public DeploymentConfigBuilder(final String name) {
        super("DeploymentConfig", "v1", name);
        me().child("spec")
                .builder("rollingStrategy", "strategy", RollingDeploymentStrategyBuilder::create)
                .builder("recreateStrategy", "strategy", RecreateDeploymentStrategyBuilder::create);
    }
}
