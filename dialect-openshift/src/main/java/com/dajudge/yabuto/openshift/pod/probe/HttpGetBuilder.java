package com.dajudge.yabuto.openshift.pod.probe;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;

public class HttpGetBuilder extends ObjectBuilder<HttpGetBuilder> {
    public HttpGetBuilder() {
        me().simpleValue("host", "host", null, String.class)
        .simpleValue("path", "path", null, String.class)
        .simpleValue("port", "port", null, int.class)
                .simpleValue("scheme", "scheme", null, String.class)
                .builderList("httpHeader", "httpHeaders", HeaderBuilder::create);
    }

    public static Builder create(final Object[] objects) {
        return new HttpGetBuilder();
    }
}
