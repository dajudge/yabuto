package com.dajudge.yabuto.k8s.base;

public class RootObjectBuilder<T extends RootObjectBuilder> extends MetadataObjectBuilder<T> {
    public RootObjectBuilder(final String kind, final String apiVersion, final String name) {
        super(name);
        me().simpleValue("kind", "kind", kind, String.class);
        me().simpleValue("apiVersion", "apiVersion", apiVersion, String.class);
    }
}
