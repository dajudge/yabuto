package com.dajudge.yabuto.testbase


import com.dajudge.yabuto.api.Entrypoint

abstract class AbstractBaseRootObjectBuilderTest {
    static final OBJECT_NAME = "someObjectName"

    abstract Entrypoint entrypoint();

    Map<String, Object> underTest(final Closure closure) {
        entrypoint().enter(OBJECT_NAME, closure) as Map<String, Object>
    }
}
