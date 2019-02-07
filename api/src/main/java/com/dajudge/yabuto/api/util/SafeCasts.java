package com.dajudge.yabuto.api.util;

public final class SafeCasts {
    private SafeCasts() {
    }

    public static String string(final Object object) {
        return object == null ? null : object.toString();
    }
}
