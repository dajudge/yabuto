package com.dajudge.yabuto.api.features;

import groovy.lang.GString;

import java.io.File;
import java.util.Map;

import static com.dajudge.yabuto.api.util.StreamUtil.loadUtf8FromFile;

class SimpleValueFeature<T> implements ApiFeature {
    private final String itemName;
    private T value;
    private final Class<T> clazz;

    SimpleValueFeature(final String itemName, final T initValue, final Class<T> clazz) {
        this.itemName = itemName;
        this.value = initValue;
        this.clazz = clazz;
    }

    @Override
    public void invoke(final Object[] args) {
        if (args[0] instanceof File) {
            this.value = readFromFile((File) args[0], clazz);
        }else if(args[0] instanceof GString) {
            this.value = (T)args[0].toString();
        } else {
            this.value = (T) args[0];
        }
    }

    public T readFromFile(final File file, final Class<T> clazz) {
        if (String.class.isAssignableFrom(clazz)) {
            return (T) loadUtf8FromFile(file);
        } else {
            throw new IllegalArgumentException("Don't know how to load " + clazz.getName() + " from file.");
        }
    }

    @Override
    public void build(final Map<String, Object> target) {
        if (value != null) {
            target.put(itemName, value);
        }
    }
}
