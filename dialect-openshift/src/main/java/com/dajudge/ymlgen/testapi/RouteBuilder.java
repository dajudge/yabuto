package com.dajudge.ymlgen.testapi;

import com.dajudge.ymlgen.api.util.RootObjectBuilder;
import groovy.lang.Closure;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;


public class RouteBuilder extends RootObjectBuilder<RouteBuilder> {
    public RouteBuilder() {
        kind("Route");
        apiVersion("v1");
    }

    public RouteBuilder host(final String host) {
        spec().put("host", host);
        return this;
    }

    public RouteBuilder targetPort(final int port) {
        port().put("targetPort", port);
        return this;
    }

    public RouteBuilder tls(final Closure<Map<String, Object>> tls) {
        spec().put("tls", callBuilderClosure(tls, new TlsBuilder()));
        return this;
    }

    private Map<String, Object> port() {
        return submap(spec(), "port");
    }

    private Map<String, Object> spec() {
        return submap(object, "spec");
    }
}
