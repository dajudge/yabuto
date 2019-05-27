package com.dajudge.yabuto.test.yaml;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;

public class YamlList extends YamlBase {
    private final List<?> list;

    public YamlList(final List<?> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String string(final int index) {
        return (String) safeCast(list.get(index), String.class);
    }

    public Integer integer(final int index) {
        return (Integer) safeCast(list.get(index), int.class, Integer.class);
    }

    public YamlObject object(final int index) {
        return new YamlObject((Map<String, Object>) safeCast(list.get(index), Map.class));
    }

    public YamlList list(final int index) {
        return new YamlList((List<?>) safeCast(list.get(index), List.class));
    }

    public void lacksObject(final Predicate<YamlObject>... filters) {
        lacksObject(asList(filters));
    }

    public void lacksObject(final Collection<Predicate<YamlObject>> filters) {
        final Set<YamlObject> result = findAllObjects(filters);
        if (!result.isEmpty()) {
            throw new AssertionError("Found unexepcted object");
        }
    }

    public YamlObject requiresObject(final Predicate<YamlObject>... filters) {
        return requiresObject(asList(filters));
    }

    public YamlObject requiresObject(final Collection<Predicate<YamlObject>> filters) {
        final Set<YamlObject> result = findAllObjects(filters);
        if (result.isEmpty()) {
            throw new AssertionError("Could not find object that matches criteria");
        }
        if (result.size() > 1) {
            throw new AssertionError("Ambiguous object filter, found " + result.size() + " matching objects");
        }
        return result.iterator().next();
    }

    public Set<YamlObject> findAllObjects(final Predicate<YamlObject>... filters) {
        return findAllObjects(asList(filters));
    }

    public Set<YamlObject> findAllObjects(final Collection<Predicate<YamlObject>> filters) {
        final Predicate<YamlObject> combinedFilter = and(filters);
        return list.stream()
                .filter(it -> it instanceof Map)
                .map(it -> new YamlObject((Map<String, Object>) it))
                .filter(combinedFilter)
                .collect(Collectors.toSet());
    }

    private static <T> Predicate<T> and(final Collection<Predicate<T>> filters) {
        return t -> filters.stream()
                .filter(it -> it.negate().test(t))
                .map(it -> false)
                .findAny()
                .orElse(true);
    }
}
