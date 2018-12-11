package com.dajudge.yabuto.openshift

import org.junit.Test

import static OpenShiftDialect.ENTRYPOINT_DEPLOYMENTCONFIG
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind

class DeploymentConfigTest {

    private static final String DEPLOYMENT_CONFIG_NAME = "someName"

    static Map<String, Object> build(final String name, final Closure closure) {
        new OpenShiftDialect()
                .getEntrypoints().get(ENTRYPOINT_DEPLOYMENTCONFIG)
                .enter(name, closure) as Map<String, Object>
    }

    @Test
    void creates_deployment_config() {
        def result = build(DEPLOYMENT_CONFIG_NAME) {}

        assertKind(DEPLOYMENT_CONFIG_NAME, "v1", "DeploymentConfig", result)
    }

    @Test
    void can_do_rolling_strategy() {
        def result = build(DEPLOYMENT_CONFIG_NAME) {
            rollingStrategy {
            }
        }

        println result
    }

}
