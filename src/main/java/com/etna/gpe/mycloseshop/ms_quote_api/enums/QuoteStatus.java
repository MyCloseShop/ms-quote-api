package com.etna.gpe.mycloseshop.ms_quote_api.enums;

public enum QuoteStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    REFUSED("REFUSED"),
    CANCELED("CANCELED");

    private final String value;

    QuoteStatus(String value) {
        this.value = value;
    }

    public static QuoteStatus fromString(String text) {
        for (QuoteStatus b : QuoteStatus.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
