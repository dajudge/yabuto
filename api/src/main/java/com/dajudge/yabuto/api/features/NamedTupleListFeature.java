package com.dajudge.yabuto.api.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedTupleListFeature implements ApiFeature {
    private final List<Object[]> argsList = new ArrayList<>();
    private final String listName;
    private final String[] names;

    public NamedTupleListFeature(final String listName, final String[] names) {
        this.listName = listName;
        this.names = names;
    }

    @Override
    public void invoke(final Object[] args) {
        this.argsList.add(args);
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (argsList.isEmpty()) {
            return;
        }
        final ArrayList<Object> entries = new ArrayList<>();
        target.put(listName, entries);
        argsList.forEach(args -> {
            final HashMap<String, Object> map = new HashMap<>();
            for (int i = 0; i < names.length; i++) {
                map.put(names[i], args[i]);
            }
            entries.add(map);
        });
    }
}
