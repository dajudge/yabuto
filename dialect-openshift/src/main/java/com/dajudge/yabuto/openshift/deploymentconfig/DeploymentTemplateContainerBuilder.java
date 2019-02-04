package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.openshift.shared.PortBuilder;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    DeploymentTemplateContainerBuilder(final String name) {
        me().simpleValue("name", "name", name, String.class)
                .simpleValue("image", "image", null, String.class)
                .simpleValue("imagePullPolicy", "imagePullPolicy", null, String.class)
                .builderList("port", "ports", args -> new PortBuilder());
    }
}
