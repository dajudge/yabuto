package com.dajudge.yabuto.api.features;

public class ConstantFeature<K> extends SimpleValueFeature<K> {

    private final K value;

    public ConstantFeature(final String itemName, final K value) {
        super(itemName, null, (Class<K>)value.getClass());
        this.value = value;
    }

    @Override
    public void invoke(final Object[] args) {
        super.invoke(new Object[]{value});
    }
}
