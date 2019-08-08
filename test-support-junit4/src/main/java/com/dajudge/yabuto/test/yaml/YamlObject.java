package com.dajudge.yabuto.test.yaml;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Collections.emptyList;

public class YamlObject extends YamlBase {
    private static final Supplier<YamlList> EMPTY_YAML_LIST = () -> new YamlList(emptyList());
    private final Map<String, Object> yaml;

    public YamlObject(final Map<String, Object> yaml) {
        this.yaml = yaml;
    }

    public YamlList list(final String prop) {
        return new YamlList((List) safeGet(prop, true, List.class));
    }

    public YamlList list(final String prop, boolean required) {
        return withFallback(required, () -> list(prop), EMPTY_YAML_LIST);
    }

    public YamlList list(final Function<YamlObject, YamlList> path) {
        return list(path, true);
    }

    public YamlList list(final Function<YamlObject, YamlList> path, final boolean required) {
        return withFallback(required, () -> path.apply(this), EMPTY_YAML_LIST);
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

    public boolean lacks(final String prop) {
        return !yaml.containsKey(prop);
    }

    private Object safeGet(final String prop, boolean required, final Class<?>... classes) {
        if (lacks(prop)) {
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
