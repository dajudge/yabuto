package com.dajudge.yabuto.openshift.shared;

import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

public class ResourcesBuilder extends ObjectBuilder<ResourcesBuilder> {
    private ResourcesBuilder() {
        me().child("limits").custom("limits", new QuotaFeature());
        me().child("requests").custom("requests", new QuotaFeature());
    }
    public static ResourcesBuilder create(final Object[] args) {
        return new ResourcesBuilder();
    }

    private class QuotaFeature implements ApiFeature {
        private String cpu;
        private String memory;

        @Override
        public void invoke(final Object[] args) {
            cpu = string(args[0]);
            memory = string(args[1]);
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (cpu != null) {
                target.put("cpu", cpu);
            }
            if (memory != null) {
                target.put("memory", memory);
            }
        }
    }
}
