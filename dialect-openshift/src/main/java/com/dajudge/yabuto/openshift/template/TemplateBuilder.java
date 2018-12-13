package com.dajudge.yabuto.openshift.template;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

import java.util.List;

public class TemplateBuilder extends RootObjectBuilder<TemplateBuilder> {
    public TemplateBuilder(final String name) {
        super("Template", "v1", name);
        me().simpleValue("objects", "objects", null, List.class);
    }
}
