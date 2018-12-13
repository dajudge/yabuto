package com.dajudge.yabuto.k8s

import com.dajudge.yabuto.k8s.deployment.DeploymentTemplateContainerBuilder
import com.dajudge.yabuto.testbase.AbstractBaseSubBuilderTest
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class DeploymentTemplateContainerTest extends AbstractBaseSubBuilderTest<DeploymentTemplateContainerBuilder> {

    @Override
    DeploymentTemplateContainerBuilder createBuilderUnderTest() {
        new DeploymentTemplateContainerBuilder("someName")
    }

    @Test
    void sets_image() {
        def result = underTest {
            image "someImageName"
        }

        assertEquals("someImageName", result.image)
    }

    @Test
    void sets_container_port() {
        def result = underTest {
            containerPort 80
        }

        List ports = result.ports
        assertNotNull(ports)
        assertEquals(1, ports.size())
        assertEquals(80, ports[0].containerPort)
    }
}
