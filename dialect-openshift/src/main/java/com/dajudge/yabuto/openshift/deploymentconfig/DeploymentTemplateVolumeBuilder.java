package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.ymlgen.api.features.ApiFeature;
import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

public class DeploymentTemplateVolumeBuilder extends ObjectBuilder<DeploymentTemplateVolumeBuilder> {
    public DeploymentTemplateVolumeBuilder(final String name) {
        me().simpleValue("name", "name", name, String.class);
        me().custom("fromConfigMap", new FromConfigMapFeature());
    }

    private static class FromConfigMapFeature implements ApiFeature {
        private String name;

        @Override
        public void invoke(final Object[] args) {
            name = string(args[0]);
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (name == null) {
                return;
            }
            Map<String, Object> configMap = new HashMap<>();
            configMap.put("name", name);
            target.put("configMap", configMap);
        }
    }
}
