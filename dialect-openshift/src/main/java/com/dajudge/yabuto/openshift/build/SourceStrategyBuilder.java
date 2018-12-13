package com.dajudge.yabuto.openshift.build;

import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.features.NamedTupleListFeature;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

public class SourceStrategyBuilder extends ObjectBuilder<SourceStrategyBuilder> {
    private SourceStrategyBuilder() {
        me().child("from")
                .custom("fromImageStreamTag", new FromImageStreamTagFeature());
        me().custom("env", new NamedTupleListFeature("env", new String[]{"name", "value"}));
    }

    public static SourceStrategyBuilder create(final Object[] args) {
        return new SourceStrategyBuilder();
    }

    private static class FromImageStreamTagFeature implements ApiFeature {
        String name;
        String namespace;

        @Override
        public void invoke(final Object[] args) {
            name = (String) args[0];
            namespace = (String) args[1];
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (name == null) {
                return;
            }
            target.put("kind", "ImageStreamTag");
            target.put("name", name);
            if (namespace != null) {
                target.put("namespace", namespace);
            }
        }
    }

}
