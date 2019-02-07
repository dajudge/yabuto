package com.dajudge.yabuto.k8s.deployment;

import com.dajudge.yabuto.api.util.ObjectBuilder;

public class RollingDeploymentStrategyBuilder extends ObjectBuilder<RollingDeploymentStrategyBuilder> {
    private RollingDeploymentStrategyBuilder() {
        me().simpleValue("type", "type", "Rolling", String.class);
        me().child("rollingParams")
                .simpleValue("intervalSeconds", "intervalSeconds", null, int.class)
                .simpleValue("maxSurge", "maxSurge", null, int.class)
                .simpleValue("maxUnavailable", "maxUnavailable", null, int.class)
                .simpleValue("timeoutSeconds", "timeoutSeconds", null, int.class)
                .simpleValue("updatePeriodSeconds", "updatePeriodSeconds", null, int.class);
    }

    public static RollingDeploymentStrategyBuilder create(final Object[] args) {
        return new RollingDeploymentStrategyBuilder();
    }
}
