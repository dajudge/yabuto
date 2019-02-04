package com.dajudge.yabuto.openshift.shared;

import com.dajudge.ymlgen.api.features.ApiFeature;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.SafeCasts.string;

public class ImageStreamTagFeature implements ApiFeature {
    private String name;

    @Override
    public void invoke(final Object[] args) {
        name = string(args[0]);
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (name != null) {
            target.put("kind", "ImageStreamTag");
            target.put("name", name);
        }
    }
}
