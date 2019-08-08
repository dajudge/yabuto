package com.dajudge.yabuto.k8s.shared;

import com.dajudge.yabuto.api.features.ApiFeature;
import com.dajudge.yabuto.api.util.ObjectBuilder;
import groovy.lang.GString;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class EnvBuilder extends ObjectBuilder<EnvBuilder> {
    EnvBuilder(final String name, final String value) {
        me().simpleValue("name", "name", name, String.class);
        me().simpleValue("value", "value", value, String.class);
        me().custom("fromSecret", new SecretKeyRefFeature());
    }

    public static EnvBuilder create(final Object[] params) {
        final String name = string(params[0]);
        final String value = isString(params[1]) ? params[1].toString() : null;
        return new EnvBuilder(name, value);
    }

    private static boolean isString(Object param) {
        return param instanceof String || param instanceof GString;
    }

    private class SecretKeyRefFeature implements ApiFeature {
        private String secret;
        private String key;

        @Override
        public void invoke(final Object[] args) {
            secret = string(args[0]);
            key = string(args[1]);
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (secret == null || key == null) {
                return;
            }
            Map<String, Object> secretKeyRef = new HashMap<>();
            secretKeyRef.put("name", secret);
            secretKeyRef.put("key", key);
            Map<String, Object> valueFrom = new HashMap<>();
            valueFrom.put("secretKeyRef", secretKeyRef);
            target.put("valueFrom", valueFrom);
        }
    }
}
