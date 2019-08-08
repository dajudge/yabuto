package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.k8s.base.MetadataObjectBuilder;
import com.dajudge.yabuto.openshift.pod.PodSpecBuilder;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    DeploymentTemplateBuilder() {
        me().child("spec")
                .simpleValue("serviceAccount", "serviceAccount", null, String.class)
                .simpleValue("serviceAccountName", "serviceAccountName", null, String.class)
                .builderList("container", "containers", this::createContainerBuilder)
                .builderList("initContainer", "initContainers", this::createContainerBuilder)
                .builderList("volume", "volumes", this::createVolumeBuilder);
    }

    private DeploymentTemplateVolumeBuilder createVolumeBuilder(final Object[] params) {
        return new DeploymentTemplateVolumeBuilder(string(params[0]));
    }

    private PodSpecBuilder createContainerBuilder(final Object[] params) {
        return new PodSpecBuilder(string(params[0]));
    }

    static Builder create(final Object[] params) {
        return new DeploymentTemplateBuilder();
    }
}
