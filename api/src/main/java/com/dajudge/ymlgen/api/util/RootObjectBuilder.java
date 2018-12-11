package com.dajudge.ymlgen.api.util;

public class RootObjectBuilder<T extends RootObjectBuilder> extends MetadataObjectBuilder<T> {

    public T kind(final String kind) {
        object.put("kind", kind);
        return me();
    }

    public T apiVersion(final String apiVersion) {
        object.put("apiVersion", apiVersion);
        return me();
    }
}
