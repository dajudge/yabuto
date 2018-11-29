package com.dajudge.yabuto.k8s;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

public class RollingDeploymentStrategyBuilder extends ObjectBuilder<RollingDeploymentStrategyBuilder> {
    public RollingDeploymentStrategyBuilder() {
        object.put("type", "Rolling");
    }

    public RollingDeploymentStrategyBuilder intervalSeconds(final int seconds) {
        params().put("intervalSeconds", seconds);
        return this;
    }

    public RollingDeploymentStrategyBuilder maxSurge(final String maxSurge) {
        params().put("maxSurge", maxSurge);
        return this;
    }

    public RollingDeploymentStrategyBuilder maxUnavailable(final String maxUnavailable) {
        params().put("maxUnavailable", maxUnavailable);
        return this;
    }

    public RollingDeploymentStrategyBuilder timeoutSeconds(int seconds) {
        params().put("timeoutSeconds", seconds);
        return this;
    }

    public RollingDeploymentStrategyBuilder updatePeriodSeconds(int seconds) {
        params().put("updatePeriodSeconds", seconds);
        return this;
    }

    private Map<String, Object> params() {
        return submap(object, "rollingParams");
    }
}
