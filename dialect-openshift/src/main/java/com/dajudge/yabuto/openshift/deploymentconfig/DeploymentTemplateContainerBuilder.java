package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.openshift.shared.EnvBuilder;
import com.dajudge.yabuto.openshift.shared.PortBuilder;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    DeploymentTemplateContainerBuilder(final String name) {
        me().simpleValue("name", "name", name, String.class)
                .simpleValue("image", "image", null, String.class)
                .simpleValue("imagePullPolicy", "imagePullPolicy", null, String.class)
                .builderList("port", "ports", args -> new PortBuilder())
                .builderList("volumeMount", "volumeMounts", this::createVolumeMountBuilder)
                .builderList("env", "env", EnvBuilder::create);
    }

    private VolumeMountBuilder createVolumeMountBuilder(final Object[] params) {
        return new VolumeMountBuilder(string(params[0]));
    }

    private class VolumeMountBuilder extends ObjectBuilder<VolumeMountBuilder> {
        VolumeMountBuilder(final String name) {
            me().simpleValue("name", "name", name, String.class)
                    .simpleValue("mountPath", "mountPath", null, String.class);
        }
    }
}
