package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;
import com.dajudge.yabuto.openshift.shared.ResourcesBuilder;
import com.dajudge.ymlgen.api.features.FeatureOwner;


public class BuildConfigBuilder extends RootObjectBuilder<BuildConfigBuilder> {
    public BuildConfigBuilder(final String name) {
        super("BuildConfig", "v1", name);
        final FeatureOwner spec = me().child("spec")
                .builder("sourceStrategy", "strategy", SourceStrategyBuilder::create)
                .builder("dockerStrategy", "strategy", DockerStrategyBuilder::create)
                .builder("resources", "resources", ResourcesBuilder::create);
        spec.child("output").child("to")
                .custom("outputToImageStreamTag", new ImageStreamTagFeature());
        spec.child("source")
                .constant("binarySource", "type", "Binary");
    }

}
