package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.yabuto.k8s.base.MetadataObjectBuilder;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    public DeploymentTemplateBuilder() {
        me().builderList("imagePullSecret", "imagePullSecrets", args -> new PullSecretsBuilder());
        me().builderList("container", "containers", DeploymentTemplateContainerBuilder::create);
    }

    private class PullSecretsBuilder extends ObjectBuilder<PullSecretsBuilder> {
        PullSecretsBuilder() {
            me().simpleValue("name", "name", null, String.class);
        }
    }
}
