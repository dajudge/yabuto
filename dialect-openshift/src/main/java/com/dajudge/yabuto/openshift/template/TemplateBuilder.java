package com.dajudge.yabuto.openshift.template;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.openshift.configmap.DataFeature;
import com.dajudge.yabuto.openshift.shared.EnvBuilder;

import java.util.List;

public class TemplateBuilder extends RootObjectBuilder<TemplateBuilder> {
    public TemplateBuilder(final String name) {
        super("Template", "v1", name);
        me().simpleValue("objects", "objects", null, List.class);
        me().builderList("parameter", "parameters", ParameterBuilder::create);
    }
}
