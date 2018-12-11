package com.dajudge.yabuto.openshift;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.Map;

public class ResourcesBuilder extends ObjectBuilder<ResourcesBuilder> {
    public ResourcesBuilder limits(final String cpu, final String memory) {
        return quota(cpu, memory, limits());
    }

    public ResourcesBuilder requests(final String cpu, final String memory) {
        return quota(cpu, memory, requests());
    }

    private ResourcesBuilder quota(final String cpu, final String memory, final Map<String, Object> map) {
        map.put("cpu", cpu);
        map.put("memory", memory);
        return this;
    }

    private Map<String, Object> limits() {
        return submap(object, "limits");
    }

    private Map<String, Object> requests() {
        return submap(object, "requests");
    }
}
