package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.RootObjectBuilder;
import groovy.lang.Closure;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;

public class DeploymentBuilder extends RootObjectBuilder<DeploymentBuilder> {
    public DeploymentBuilder() {
        kind("Deployment");
        apiVersion("apps/v1");
    }

    public DeploymentBuilder matchLabel(final String key, final String value) {
        matchLabels().put(key, value);
        return me();
    }

    public DeploymentBuilder template(final Closure<?> template) {
        spec().put("template", callBuilderClosure(template, new DeploymentTemplateBuilder()));
        return me();
    }

    private Map<String, Object> matchLabels() {
        return submap(spec(), "matchLabels");
    }

    private Map<String, Object> spec() {
        return submap(object, "spec");
    }
}
