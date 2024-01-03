package com.zara.pricecalculator.domain.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zara.pricecalculator.application.dto.price.PriceDTO;
import lombok.Data;

@Data
public class PriceDTOResponse {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("data")
    private PriceDTO data;

    public PriceDTOResponse(boolean status, PriceDTO data) {
        this.status = status;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }
}

