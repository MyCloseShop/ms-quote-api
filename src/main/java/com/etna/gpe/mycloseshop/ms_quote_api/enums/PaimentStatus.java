package com.etna.gpe.mycloseshop.ms_quote_api.enums;

public enum PaimentStatus {
    PENDING("PENDING"),
    PAID("PAID"),
    REFUNDED("REFUNDED"),
    CANCELED("CANCELED");

    private final String value;

    PaimentStatus(String value) {
        this.value = value;
    }

    public static PaimentStatus fromString(String text) {
        for (PaimentStatus b : PaimentStatus.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
