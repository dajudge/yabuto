package com.dajudge.yabuto.openshift.pod.probe;

import com.dajudge.yabuto.api.util.ObjectBuilder;

public class ProbeBuilder extends ObjectBuilder<ProbeBuilder> {

    public ProbeBuilder() {
        me().simpleValue("failureThreshold", "failureThreshold", null, int.class)
                .simpleValue("initialDelaySeconds", "initialDelaySeconds", null, int.class)
                .simpleValue("periodSeconds", "periodSeconds", null, int.class)
                .simpleValue("successThreshold", "successThreshold", null, int.class)
                .simpleValue("timeoutSeconds", "timeoutSeconds", null, int.class)
                .builder("httpGet", "httpGet", HttpGetBuilder::create)
                .builder("exec", "exec", ExecBuilder::create)
                .builder("tcpSocket", "tcpSocket", TcpSockerBuilder::create);
    }

    public static ProbeBuilder create(final Object[] objects) {
        return new ProbeBuilder();
    }
}
