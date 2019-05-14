package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.api.util.ObjectBuilder;
import com.dajudge.yabuto.openshift.shared.EnvBuilder;
import com.dajudge.yabuto.openshift.shared.PortBuilder;
import com.dajudge.yabuto.openshift.shared.ResourcesBuilder;

import java.util.List;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    DeploymentTemplateContainerBuilder(final String name) {
        me().simpleValue("name", "name", name, String.class)
                .simpleValue("image", "image", null, String.class)
                .simpleValue("imagePullPolicy", "imagePullPolicy", null, String.class)
                .simpleValue("command", "command", null, List.class)
                .simpleValue("args", "args", null, List.class)
                .builderList("port", "ports", args -> new PortBuilder())
                .builderList("volumeMount", "volumeMounts", VolumeMountBuilder::create)
                .builderList("env", "env", EnvBuilder::create)
                .builder("resources", "resources", ResourcesBuilder::create);
    }

    private static class VolumeMountBuilder extends ObjectBuilder<VolumeMountBuilder> {
        VolumeMountBuilder(final String name) {
            me().simpleValue("name", "name", name, String.class)
                    .simpleValue("mountPath", "mountPath", null, String.class)
                    .simpleValue("subPath", "subPath", null, String.class);
        }

        private static VolumeMountBuilder create(final Object[] params) {
            return new VolumeMountBuilder(string(params[0]));
        }

        @Override
        public Object invokeMethod(String name, Object args) {
            return super.invokeMethod(name, args);
        }
    }
}
