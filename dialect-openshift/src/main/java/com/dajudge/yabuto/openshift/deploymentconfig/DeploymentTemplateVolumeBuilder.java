package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.api.features.ApiFeature;
import com.dajudge.yabuto.api.util.ObjectBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class DeploymentTemplateVolumeBuilder extends ObjectBuilder<DeploymentTemplateVolumeBuilder> {
    public DeploymentTemplateVolumeBuilder(final String name) {
        me().simpleValue("name", "name", name, String.class);
        me().custom("fromConfigMap", new FromFeature("configMap", "name"));
        me().custom("fromSecret", new FromFeature("secret", "secretName"));
    }

    private static class FromFeature implements ApiFeature {
        private final String containerKey;
        private final String nameKey;
        private String name;

        public FromFeature(final String containerKey, final String nameKey) {
            this.containerKey = containerKey;
            this.nameKey = nameKey;
        }

        @Override
        public void invoke(final Object[] args) {
            name = string(args[0]);
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (name == null) {
                return;
            }
            Map<String, Object> container = new HashMap<>();
            container.put(nameKey, name);
            target.put(containerKey, container);
        }
    }
}
