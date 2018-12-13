package com.dajudge.yabuto.openshift.route;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

public class TlsBuilder extends ObjectBuilder<TlsBuilder> {
    private TlsBuilder() {
        me().constant("reencrypt", "termination", "reencrypt");
        me().simpleValue("key", "key", null, String.class);
        me().simpleValue("certificate", "certificate", null, String.class);
        me().simpleValue("destinationCACertificate", "destinationCACertificate", null, String.class);
    }

    public static ObjectBuilder<?> create(final Object[] args) {
        return new TlsBuilder();
    }
}
