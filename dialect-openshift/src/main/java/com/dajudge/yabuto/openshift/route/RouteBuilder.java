package com.dajudge.yabuto.openshift.route;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.api.features.ApiFeature;
import com.dajudge.yabuto.api.features.FeatureOwner;

import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;


public class RouteBuilder extends RootObjectBuilder<RouteBuilder> {
    public RouteBuilder(final String name) {
        super("Route", "v1", name);
        final FeatureOwner spec = me().child("spec")
                .simpleValue("host", "host", null, String.class)
                .builder("tls", "tls", TlsBuilder::create);
        spec.child("to")
                .custom("toService", new RouteToServiceBuilder());
        spec.child("port")
                .simpleValue("targetPort", "targetPort", null, int.class);
    }

    private static class RouteToServiceBuilder implements ApiFeature {
        private String name;

        @Override
        public void invoke(final Object[] args) {
            name = string(args[0]);
        }

        @Override
        public void build(Map<String, Object> target) {
            if (name == null) {
                return;
            }
            target.put("kind", "Service");
            target.put("name", name);
        }
    }
}
