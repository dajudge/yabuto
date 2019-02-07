package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.k8s.base.MetadataObjectBuilder;
import com.dajudge.yabuto.api.features.Builder;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    DeploymentTemplateBuilder() {
        me().child("spec")
                .builderList("container", "containers", this::createContainerBuilder)
                .builderList("volume", "volumes", this::createVolumeBuidler);
    }

    private DeploymentTemplateVolumeBuilder createVolumeBuidler(final Object[] params) {
        return new DeploymentTemplateVolumeBuilder(string(params[0]));
    }

    private DeploymentTemplateContainerBuilder createContainerBuilder(final Object[] params) {
        return new DeploymentTemplateContainerBuilder(string(params[0]));
    }

    static Builder create(final Object[] params) {
        return new DeploymentTemplateBuilder();
    }
}
