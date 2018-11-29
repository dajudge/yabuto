package com.dajudge.ymlgen.testapi;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SourceStrategyBuilder extends ObjectBuilder<SourceStrategyBuilder> {
    public SourceStrategyBuilder fromImageStreamTag(final String name, final String namespace) {
        from().put("kind", "ImageStreamTag");
        from().put("name", name);
        from().put("namespace", namespace);
        return this;
    }

    public SourceStrategyBuilder env(final String key, final String value) {
        final Map<String, Object> param = new HashMap<>();
        param.put("name", key);
        param.put("value", value);
        env().add(param);
        return this;
    }

    private Map<String, Object> from() {
        return submap(object, "from");
    }

    private List<Object> env() {
        return sublist(object, "env");
    }
}
