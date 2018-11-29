package com.dajudge.yabuto.k8s;

import com.dajudge.ymlgen.api.util.RootObjectBuilder;
import groovy.lang.Closure;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;

public class DeploymentConfigBuilder extends RootObjectBuilder<DeploymentConfigBuilder> {
    public DeploymentConfigBuilder() {
        kind("DeploymentConfig");
        apiVersion("v1");
    }

    public DeploymentConfigBuilder rollingStrategy(final Closure<HashMap<String, Object>> strategy) {
        spec().put("strategy", callBuilderClosure(strategy, new RollingDeploymentStrategyBuilder()));
        return this;
    }

    public DeploymentConfigBuilder recreateStrategy(final Closure<HashMap<String, Object>> strategy) {
        spec().put("strategy", callBuilderClosure(strategy, new RecreateDeploymentStrategyBuilder()));
        return this;
    }

    private Map<String, Object> spec() {
        return submap(object, "spec");
    }
}
