package com.dajudge.yabuto.openshift

import com.dajudge.yabuto.testbase.AbstractBaseRootObjectBuilderTest
import com.dajudge.ymlgen.api.Entrypoint
import org.junit.Test

import static com.dajudge.yabuto.openshift.OpenShiftDialect.ENTRYPOINT_BUILDCONFIG
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class BuildConfigTest extends AbstractBaseRootObjectBuilderTest {

    @Override
    Entrypoint entrypoint() {
        new OpenShiftDialect().getEntrypoints().get(ENTRYPOINT_BUILDCONFIG)
    }

    @Test
    void "creates build config"() {
        def result = underTest {}

        assertKind(OBJECT_NAME, "v1", "BuildConfig", result)
    }

    @Test
    void "is empty when nothing was set"() {
        def result = underTest {}
        assertEquals(3, result.size())
    }

    @Test
    void "can build sourceStrategy with env"() {
        def result = underTest {
            sourceStrategy {
                env "name", "value"
            }
        }
        List env = result.spec?.strategy?.sourceStrategy?.env
        assertNotNull(env)
        assertEquals([[
                              name : "name",
                              value: "value"
                      ]], env)
    }

    @Test
    void "can build with resources quotas"() {
        def result = underTest {
            resources {
                limits "cpuLimit", "memoryLimit"
                requests "cpuRequest", "memoryRequest"
            }
        }

        def resources = result.spec?.resources
        assertNotNull(resources)
        def limits = resources?.limits
        def requests = resources?.requests
        assertNotNull(limits)
        assertNotNull(requests)
        assertEquals("cpuLimit", limits.cpu)
        assertEquals("memoryLimit", limits.memory)
        assertEquals("cpuRequest", requests.cpu)
        assertEquals("memoryRequest", requests.memory)
    }

    @Test
    void "can build sourceStrategy from ImageStreamTag"() {
        def result = underTest {
            sourceStrategy {
                fromImageStreamTag "imageName", "namespaceName"
            }
        }

        def from = result.spec?.strategy?.sourceStrategy?.from
        assertNotNull(from)
        assertEquals("imageName", from.name)
        assertEquals("namespaceName", from.namespace)
        assertEquals("ImageStreamTag", from.kind)
    }

    @Test
    void "can output to ImageStreamTag"() {
        def result = underTest {
            outputToImageStreamTag "tagName"
        }

        def outputTo = result.spec?.output?.to

        assertNotNull(outputTo)
        assertEquals("tagName", outputTo.name)
        assertEquals("ImageStreamTag", outputTo.kind)
    }

}
