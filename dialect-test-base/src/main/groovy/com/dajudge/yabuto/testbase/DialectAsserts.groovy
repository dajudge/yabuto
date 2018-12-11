package com.dajudge.yabuto.testbase

import org.junit.Assert

final class DialectAsserts {
    private DialectAsserts() {
    }


    static void assertKind(String name, String apiVersion, String kind, Map<String, Object> result) {
        Assert.assertEquals(name, result.metadata?.name)
        Assert.assertEquals(apiVersion, result.apiVersion)
        Assert.assertEquals(kind, result.kind)
    }
}
