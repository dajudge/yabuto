package com.dajudge.yabuto.openshift.build;

import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

public class DockerStrategyBuilder extends ObjectBuilder<DockerStrategyBuilder> {
    private DockerStrategyBuilder() {
        me().child("from")
                .custom("fromDockerImage", new FromDockerImageFeature());
    }

    public static DockerStrategyBuilder create(final Object[] args) {
        return new DockerStrategyBuilder();
    }

    private static class FromDockerImageFeature implements ApiFeature {
        private String name;

        @Override
        public void invoke(Object[] args) {
            name = string(args[0]);
        }

        @Override
        public void build(Map<String, Object> target) {
            if (name == null) {
                return;
            }
            target.put("kind", "DockerImage");
            target.put("name", name);
        }
    }
}
