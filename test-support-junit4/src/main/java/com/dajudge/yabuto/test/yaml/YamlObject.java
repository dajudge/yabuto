package com.dajudge.yabuto.test.yaml;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class YamlObject extends YamlBase {
    private final Map<String, Object> yaml;

    public YamlObject(final Map<String, Object> yaml) {
        this.yaml = yaml;
    }

    public YamlList list(final String prop) {
        return new YamlList((List) safeGet(prop, true, List.class));
    }

    public YamlList list(final Function<YamlObject, YamlList> path) {
        return list(path, true);
    }

    public YamlList list(final Function<YamlObject, YamlList> path, final boolean required) {
        try {
            return path.apply(this);
        } catch (final AssertionError e) {
            if (required) {
                throw e;
            }
            return null;
        }
    }

    public String string(final String prop) {
        return (String) safeGet(prop, true, String.class);
    }

    public boolean hasString(final String prop, final String value) {
        return value.equals(safeGet(prop, false, String.class));
    }

    public Integer integer(final String prop) {
        return (Integer) safeGet(prop, true, int.class, Integer.class);
    }

    public boolean hasInteger(final String prop, final Integer value) {
        return value.equals(safeGet(prop, false, int.class, Integer.class));
    }

    private Object safeGet(final String prop, boolean required, final Class<?>... classes) {
        if (!yaml.containsKey(prop)) {
            if (required) {
                throw new AssertionError("No property found: " + prop);
            } else {
                return null;
            }
        }
        return safeCast(yaml.get(prop), classes);
    }

    public YamlObject object(final String prop) {
        return new YamlObject((Map<String, Object>) safeGet(prop, true, Map.class));
    }
}
