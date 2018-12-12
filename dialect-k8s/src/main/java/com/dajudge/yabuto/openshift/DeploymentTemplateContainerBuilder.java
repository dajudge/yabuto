package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.HashMap;
import java.util.List;

public class DeploymentTemplateContainerBuilder extends ObjectBuilder<DeploymentTemplateContainerBuilder> {
    public DeploymentTemplateContainerBuilder(final String name) {
        object.put("name", name);
    }

    public DeploymentTemplateContainerBuilder image(final String image) {
        object.put("image", image);
        return me();
    }

    public DeploymentTemplateContainerBuilder containerPort(final int port) {
        ports().add(new HashMap<String, Object>() {{
            put("containerPort", port);
        }});
        return me();
    }

    private List<Object> ports() {
        return sublist(object, "ports");
    }
}
