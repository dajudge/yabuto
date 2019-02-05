package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.openshift.shared.EnvBuilder;
import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;
import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.features.FeatureOwner;
import com.dajudge.ymlgen.api.features.NamedTupleListFeature;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

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
