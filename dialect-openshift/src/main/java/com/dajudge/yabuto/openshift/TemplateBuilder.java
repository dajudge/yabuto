package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.RootObjectBuilder;

import java.util.List;

public class TemplateBuilder extends RootObjectBuilder<TemplateBuilder> {
    public TemplateBuilder() {
        kind("Template");
        apiVersion("v1");
    }

    public TemplateBuilder objects(final List<Object> objects) {
        object.put("objects", objects);
        return this;
    }
}
