package com.etna.gpe.mycloseshop.ms_quote_api.dto;

import com.etna.gpe.mycloseshop.ms_quote_api.enums.PaimentStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.PresationStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.QuoteStatus;
import com.etna.gpe.mycloseshop.ms_quote_api.enums.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record QuoteDto(
        String quoteId,
        String userId,
        String shopId,
        String name,
        String description,
        Double price,
        Type type,
        Integer scheduledHours,
        Integer takenHours,
        QuoteStatus quoteStatus,
        PaimentStatus paimentStatus,
        PresationStatus prestationStatus,
        @JsonProperty("created_at")
        Instant createdAt,
        @JsonProperty("updated_at")
        Instant updatedAt
) {
}
