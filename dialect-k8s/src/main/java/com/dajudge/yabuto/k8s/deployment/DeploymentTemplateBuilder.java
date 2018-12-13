package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.yabuto.k8s.base.MetadataObjectBuilder;

public class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    public DeploymentTemplateBuilder() {
        me().simpleList("imagePullSecret", "imagePullSecrets", "name", String.class);
        me().builderList("container", "containers", DeploymentTemplateContainerBuilder::create);
    }
}
