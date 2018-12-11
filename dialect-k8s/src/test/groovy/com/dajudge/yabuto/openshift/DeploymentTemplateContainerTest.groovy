package com.dajudge.yabuto.openshift

import org.junit.Test

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure
import static org.junit.Assert.assertEquals

class DeploymentTemplateContainerTest {
    @Test
    void sets_image() {
        def result = container {
            image "someImageName"
        }

        assertEquals("someImageName", result.image)
    }

    private static Map<String, Object> container(Closure closure) {
        callBuilderClosure(closure, new DeploymentTemplateContainerBuilder("someName"))
    }
}
