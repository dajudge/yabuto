package com.dajudge.yabuto.openshift.route;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.ymlgen.api.features.FeatureOwner;


public class RouteBuilder extends RootObjectBuilder<RouteBuilder> {
    public RouteBuilder(final String name) {
        super("Route", "v1", name);
        final FeatureOwner spec = me().child("spec")
                .simpleValue("host", "host", null, String.class)
                .builder("tls", "tls", TlsBuilder::create);
        spec.child("port")
                .simpleValue("targetPort", "targetPort", null, int.class);
    }
}
