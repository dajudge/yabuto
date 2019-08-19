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
        me().custom("fromSecret", new CustomKeyRefFeature("secretKeyRef"));
        me().custom("fromConfigMap", new CustomKeyRefFeature("configMapKeyRef"));
    }

    public static EnvBuilder create(final Object[] params) {
        final String name = string(params[0]);
        final String value = isString(params[1]) ? params[1].toString() : null;
        return new EnvBuilder(name, value);
    }

    private static boolean isString(Object param) {
        return param instanceof String || param instanceof GString;
    }


    private class CustomKeyRefFeature implements ApiFeature {
        private final String refType;
        private String refName;
        private String key;

        public CustomKeyRefFeature(final String refType) {
            this.refType = refType;
        }

        @Override
        public void invoke(final Object[] args) {
            refName = string(args[0]);
            key = string(args[1]);
        }

        @Override
        public void build(final Map<String, Object> target) {
            if (refName == null || key == null) {
                return;
            }
            Map<String, Object> keyRef = new HashMap<>();
            keyRef.put("name", refName);
            keyRef.put("key", key);
            Map<String, Object> valueFrom = new HashMap<>();
            valueFrom.put(refType, keyRef);
            target.put("valueFrom", valueFrom);
        }
    }
}
