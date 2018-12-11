package com.dajudge.yabuto.openshift

import org.junit.Test

import static com.dajudge.yabuto.openshift.KubernetesDialect.ENTRYPOINT_DEPLOYMENT
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

class DeploymentTest {

    private static final String DEPLOYMENT_NAME = "someName"

    static Map<String, Object> build(final String name, final Closure closure) {
        new KubernetesDialect()
                .getEntrypoints().get(ENTRYPOINT_DEPLOYMENT)
                .enter(name, closure) as Map<String, Object>
    }

    @Test
    void has_correct_kind() {
        def result = build(DEPLOYMENT_NAME) {}

        assertKind(DEPLOYMENT_NAME, "apps/v1", "Deployment", result)
    }

    @Test
    void adds_match_labels() {
        def result = build(DEPLOYMENT_NAME) {
            matchLabel("app", "appName")
        }

        assertEquals(result.spec?.matchLabels?.app, "appName")
    }

    @Test
    void adds_template() {
        def result = build(DEPLOYMENT_NAME) {
            template {
                label("app", "appName")
            }
        }

        def metadata = result.spec?.template?.metadata
        assertNotNull(metadata)
        assertEquals("appName", metadata.labels?.app)
    }

    @Test
    void adds_containers() {
        def result = build(DEPLOYMENT_NAME) {
            template {
                container("myContainer") {
                }
            }
        }
        List containers = result.spec?.template?.containers
        assertNotNull(containers)
        assertEquals(1, containers.size())
        assertEquals("myContainer", containers[0].name)
    }
}
