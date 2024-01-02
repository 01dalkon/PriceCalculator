package com.zara.pricecalculator.domain.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceRequest {
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty("startDate")
    private LocalDateTime startDate;

    @NotNull
    @JsonProperty("brandId")
    private int brandId;

    @NotNull
    @JsonProperty("productId")
    private int productId;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getProductId() {
        return productId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
