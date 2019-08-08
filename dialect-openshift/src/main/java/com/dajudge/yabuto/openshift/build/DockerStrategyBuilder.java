package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.openshift.shared.DockerImageFeature;
import com.dajudge.yabuto.k8s.shared.EnvBuilder;
import com.dajudge.yabuto.api.features.FeatureOwner;
import com.dajudge.yabuto.api.util.ObjectBuilder;
import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;

public class DockerStrategyBuilder extends ObjectBuilder<DockerStrategyBuilder> {
    private DockerStrategyBuilder() {
        me().simpleValue("type", "type", "Docker", String.class);
        final FeatureOwner dockerStrategy = me().child("dockerStrategy");
        dockerStrategy.child("from")
                .custom("fromDockerImage", new DockerImageFeature())
                .custom("fromImageStreamTag", new ImageStreamTagFeature());
        dockerStrategy.builderList("env", "env", EnvBuilder::create);
        dockerStrategy.builderList("buildArg", "buildArgs", EnvBuilder::create);
    }

    static DockerStrategyBuilder create(final Object[] args) {
        return new DockerStrategyBuilder();
    }

}
