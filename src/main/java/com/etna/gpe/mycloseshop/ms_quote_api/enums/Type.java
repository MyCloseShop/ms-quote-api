package com.etna.gpe.mycloseshop.ms_quote_api.enums;

public enum Type {
    FLAT_RATE("FLAT_RATE"),
    HOURLY_RATE("HOURLY_RATE");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public static Type fromString(String text) {
        for (Type b : Type.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
