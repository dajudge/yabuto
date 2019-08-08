package com.dajudge.yabuto.k8s.secret;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class OpaqueSecretBuilder extends RootObjectBuilder<OpaqueSecretBuilder> {
    public OpaqueSecretBuilder(final String name) {
        super("Secret", "v1", name);
        me().simpleValue("type", "type", "opaque", String.class);
        me().keyValuePairs("data", "stringData");
    }
}
