package com.dajudge.yabuto.testbase

import com.dajudge.yabuto.api.util.ObjectBuilder

import static com.dajudge.yabuto.api.features.Builder.callBuilderClosure

abstract class AbstractBaseSubBuilderTest<T extends ObjectBuilder> {
    Map<String, Object> underTest(Closure closure) {
        def builder = createBuilderUnderTest()
        callBuilderClosure(closure, builder)
        builder.build()
    }

    abstract T createBuilderUnderTest()
}
