package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.MetadataObjectBuilder;
import groovy.lang.Closure;

import java.util.List;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;

public class DeploymentTemplateBuilder extends MetadataObjectBuilder<DeploymentTemplateBuilder> {
    public DeploymentTemplateBuilder container(final String name, final Closure<?> container) {
        containers().add(callBuilderClosure(container, new DeploymentTemplateContainerBuilder(name)));
        return this;
    }

    private List<Object> containers() {
        return sublist(object, "containers");
    }
}
