package com.dajudge.yabuto.openshift.shared;

import com.dajudge.yabuto.api.features.ApiFeature;

import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class ImageStreamTagFeature implements ApiFeature {
    private String name;
    private String namespace;

    @Override
    public void invoke(final Object[] args) {
        name = string(args[0]);
        if (args.length > 1) {
            namespace = string(args[1]);
        }
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (name != null) {
            target.put("kind", "ImageStreamTag");
            target.put("name", name);
            if (namespace != null) {
                target.put("namespace", namespace);
            }
        }
    }
}
