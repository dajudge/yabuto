package com.dajudge.yabuto.api.features;

import java.util.Map;

public interface ApiFeature {
    void invoke(final Object[] args);

    void build(final Map<String, Object> target);
}
