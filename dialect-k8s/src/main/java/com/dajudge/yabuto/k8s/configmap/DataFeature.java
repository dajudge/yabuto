package com.dajudge.yabuto.k8s.configmap;

import com.dajudge.yabuto.api.features.ApiFeature;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class DataFeature implements ApiFeature {
    private Map<String, String> data = new HashMap<>();

    @Override
    public void invoke(final Object[] args) {
        data.put(string(args[0]), string(args[1]));
    }

    @Override
    public void build(final Map<String, Object> target) {
        target.put("data", data);
    }
}
