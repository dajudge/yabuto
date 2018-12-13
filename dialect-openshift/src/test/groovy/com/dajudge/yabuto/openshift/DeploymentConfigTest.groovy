package com.dajudge.yabuto.openshift


import com.dajudge.yabuto.testbase.AbstractBaseRootObjectBuilderTest
import com.dajudge.ymlgen.api.Entrypoint
import org.junit.Test

import static com.dajudge.yabuto.openshift.OpenShiftDialect.ENTRYPOINT_DEPLOYMENTCONFIG
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind
import static org.junit.Assert.assertEquals

class DeploymentConfigTest extends AbstractBaseRootObjectBuilderTest {

    @Override
    Entrypoint entrypoint() {
        new OpenShiftDialect().getEntrypoints().get(ENTRYPOINT_DEPLOYMENTCONFIG)
    }

    @Test
    void creates_deployment_config() {
        def result = underTest {}

        assertKind(OBJECT_NAME, "v1", "DeploymentConfig", result)
    }

    @Test
    void can_do_rolling_strategy() {
        def result = underTest {
            rollingStrategy {
            }
        }
        assertEquals("Rolling", result.spec?.strategy?.type)
    }
}
