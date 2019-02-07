package com.dajudge.yabuto.openshift.shared;

import com.dajudge.yabuto.api.features.ApiFeature;

import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class DockerImageFeature implements ApiFeature {
    private String name;

    @Override
    public void invoke(Object[] args) {
        name = string(args[0]);
    }

    @Override
    public void build(Map<String, Object> target) {
        if (name == null) {
            return;
        }
        target.put("kind", "DockerImage");
        target.put("name", name);
    }
}
