package com.dajudge.yabuto.openshift.configmap;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class ConfigMapBuilder extends RootObjectBuilder<ConfigMapBuilder> {
    public ConfigMapBuilder(final String name) {
        super("ConfigMap", "v1", name);
        me().custom("data", new DataFeature());
    }
}
