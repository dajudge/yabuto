package com.dajudge.ymlgen.testapi;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.io.File;

import static com.dajudge.ymlgen.api.util.StreamUtil.loadUtf8FromFile;

public class TlsBuilder extends ObjectBuilder<TlsBuilder> {
    public TlsBuilder termination(final String termination) {
        object.put("termination", termination);
        return this;
    }

    public TlsBuilder key(final File file) {
        return key(loadUtf8FromFile(file));
    }

    public TlsBuilder key(final String key) {
        object.put("key", key);
        return this;
    }

    public TlsBuilder certificate(final File file) {
        return certificate(loadUtf8FromFile(file));
    }

    public TlsBuilder certificate(final String cert) {
        object.put("certificate", cert);
        return this;
    }

    public TlsBuilder destinationCACertificate(final File file) {
        return destinationCACertificate(loadUtf8FromFile(file));
    }

    public TlsBuilder destinationCACertificate(final String cert) {
        object.put("destinationCACertificate", cert);
        return this;
    }

}
