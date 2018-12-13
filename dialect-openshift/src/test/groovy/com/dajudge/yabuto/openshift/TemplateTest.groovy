package com.dajudge.yabuto.openshift

import com.dajudge.yabuto.testbase.AbstractBaseRootObjectBuilderTest
import com.dajudge.ymlgen.api.Entrypoint
import org.junit.Test

import static com.dajudge.yabuto.openshift.OpenShiftDialect.ENTRYPOINT_TEMPLATE
import static com.dajudge.yabuto.testbase.DialectAsserts.assertKind
import static org.junit.Assert.assertEquals

class TemplateTest extends AbstractBaseRootObjectBuilderTest {
    @Override
    Entrypoint entrypoint() {
        return new OpenShiftDialect().getEntrypoints().get(ENTRYPOINT_TEMPLATE)
    }

    @Test
    void "creates template"() {
        def list = ["a", "b"]
        def result = underTest {
            objects list
        }
        assertKind(OBJECT_NAME, "v1", "Template", result)
        assertEquals(list, result.objects)
    }
}
