package com.etna.gpe.mycloseshop.ms_quote_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TakenHoursDto {
    @JsonProperty("taken_hours")
    @Min(0)
    Integer takenHours;
}
