package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.MetadataObjectBuilder;
import groovy.lang.Closure;

import java.util.HashMap;
import java.util.List;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;

public class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    public DeploymentTemplateBuilder container(final String name, final Closure<?> container) {
        containers().add(callBuilderClosure(container, new DeploymentTemplateContainerBuilder(name)));
        return me();
    }

    public DeploymentTemplateBuilder imagePullSecret(final String name) {
        imagePullSecrets().add(new HashMap<String, Object>() {{
            put("name", name);
        }});
        return me();
    }

    private List<Object> imagePullSecrets() {
        return sublist(object, "imagePullSecrets");
    }

    private List<Object> containers() {
        return sublist(object, "containers");
    }
}
