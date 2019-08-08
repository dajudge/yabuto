package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.k8s.shared.EnvBuilder;
import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;
import com.dajudge.yabuto.api.features.FeatureOwner;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class SourceStrategyBuilder extends ObjectBuilder<SourceStrategyBuilder> {
    private SourceStrategyBuilder() {
        me().simpleValue("type", "type", "Source", String.class);
        final FeatureOwner sourceStrategy = me().child("sourceStrategy");
        sourceStrategy.child("from")
                .custom("fromImageStreamTag", new ImageStreamTagFeature());
        sourceStrategy.builderList("env", "env", EnvBuilder::create);
    }

    static SourceStrategyBuilder create(final Object[] args) {
        return new SourceStrategyBuilder();
    }
}
