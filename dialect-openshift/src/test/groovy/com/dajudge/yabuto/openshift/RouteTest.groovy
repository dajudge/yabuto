package com.dajudge.yabuto.openshift

import com.dajudge.yabuto.api.Entrypoint
import org.junit.Test

import static com.dajudge.yabuto.openshift.OpenShiftDialect.ENTRYPOINT_ROUTE
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class RouteTest extends com.dajudge.yabuto.testbase.AbstractBaseRootObjectBuilderTest {
    @Override
    Entrypoint entrypoint() {
        return new OpenShiftDialect().getEntrypoints().get(ENTRYPOINT_ROUTE)
    }

    @Test
    void "creates route"() {
        def result = underTest {}
        assertKind(OBJECT_NAME, "v1", "Route", result)
    }

    @Test
    void "sets reencrypt termination"() {
        def result = underTest {
            tls {
                reencrypt()
            }
        }

        assertEquals("reencrypt", result.spec?.tls?.termination)
    }

    @Test
    void "sets host"() {
        def result = underTest {
            host "lokalhorst"
        }

        assertEquals("lokalhorst", result.spec?.host)
    }

    @Test
    void "set targetPort"() {
        def result = underTest {
            targetPort 80
        }

        assertEquals(80, result.spec?.port?.targetPort)
    }

    @Test
    void "reads key from file"() {
        def file = File.createTempFile("yabuto_", "_test")
        def fileContent = "Hello, world"
        file.text = fileContent
        def result = underTest {
            tls {
                key file
                certificate file
                destinationCACertificate file
            }
        }
        def tls = result.spec?.tls
        assertNotNull(tls)
        assertEquals(fileContent, tls.key)
        assertEquals(fileContent, tls.certificate)
        assertEquals(fileContent, tls.destinationCACertificate)
    }
}
