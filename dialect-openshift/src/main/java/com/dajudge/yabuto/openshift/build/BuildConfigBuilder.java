package com.dajudge.yabuto.openshift.build;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.openshift.shared.ResourcesBuilder;
import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.features.FeatureOwner;

import java.util.Map;


public class BuildConfigBuilder extends RootObjectBuilder<BuildConfigBuilder> {
    public BuildConfigBuilder(final String name) {
        super("BuildConfig", "v1", name);
        final FeatureOwner spec = me().child("spec")
                .builder("sourceStrategy", "sourceStrategy", SourceStrategyBuilder::create)
                .builder("resources", "resources", ResourcesBuilder::create);
        spec.child("output").child("to")
                .custom("outputToImageStreamTag", new OutputToImageStreamTagFeature());
        spec.child("source")
                .constant("binarySource", "type", "Binary");
    }

    private static class OutputToImageStreamTagFeature implements ApiFeature {
        private String name;

        @Override
        public void invoke(final Object[] args) {
            name = (String) args[0];
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (name != null) {
                target.put("kind", "ImageStreamTag");
                target.put("name", name);
            }
        }
    }
}
