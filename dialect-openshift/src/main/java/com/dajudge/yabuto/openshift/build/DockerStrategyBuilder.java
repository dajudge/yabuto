package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.openshift.shared.DockerImageFeature;
import com.dajudge.yabuto.openshift.shared.EnvBuilder;
import com.dajudge.yabuto.api.features.FeatureOwner;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class DockerStrategyBuilder extends ObjectBuilder<DockerStrategyBuilder> {
    private DockerStrategyBuilder() {
        me().simpleValue("type", "type", "Docker", String.class);
        final FeatureOwner dockerStrategy = me().child("dockerStrategy");
        dockerStrategy.child("from")
                .custom("fromDockerImage", new DockerImageFeature());
        dockerStrategy.builderList("env", "env", EnvBuilder::create);
    }

    static DockerStrategyBuilder create(final Object[] args) {
        return new DockerStrategyBuilder();
    }

}
