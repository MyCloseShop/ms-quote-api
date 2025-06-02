package com.etna.gpe.mycloseshop.ms_quote_api.enums;

public enum PresationStatus {
    PENDING("PENDING"),
    DONE("DONE"),
    CANCELED("CANCELED");

    private final String value;

    PresationStatus(String value) {
        this.value = value;
    }

    public static PresationStatus fromString(String text) {
        for (PresationStatus b : PresationStatus.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
