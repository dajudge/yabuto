package com.dajudge.yabuto.openshift.imagestream;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.k8s.deployment.RecreateDeploymentStrategyBuilder;
import com.dajudge.yabuto.k8s.deployment.RollingDeploymentStrategyBuilder;

public class ImageStreamBuilder extends RootObjectBuilder<ImageStreamBuilder> {
    public ImageStreamBuilder(final String name) {
        super("ImageStream", "v1", name);
    }
}
