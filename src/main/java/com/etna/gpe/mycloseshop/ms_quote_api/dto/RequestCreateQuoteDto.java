package com.etna.gpe.mycloseshop.ms_quote_api.dto;

import jakarta.validation.constraints.NotNull;

public record RequestCreateQuoteDto(
        @NotNull
        String userId,
        @NotNull
        String shopId,
        @NotNull
        String name,
        String description,
        @NotNull
        Double price,
        @NotNull
        String type,
        @NotNull
        Integer scheduledHours,
        @NotNull
        Integer takenHours
) {
}
