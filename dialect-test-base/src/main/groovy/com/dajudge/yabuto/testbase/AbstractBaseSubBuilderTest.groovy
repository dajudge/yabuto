package com.dajudge.yabuto.testbase

import com.dajudge.ymlgen.api.util.ObjectBuilder

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure

abstract class AbstractBaseSubBuilderTest<T extends ObjectBuilder> {
    Map<String, Object> underTest(Closure closure) {
        callBuilderClosure closure, createBuilderUnderTest()
    }

    abstract T createBuilderUnderTest()
}
