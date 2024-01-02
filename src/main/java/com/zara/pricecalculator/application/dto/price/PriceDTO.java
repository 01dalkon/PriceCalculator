package com.zara.pricecalculator.application.dto.price;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceDTO {
    private int productId;
    private int brandId;
    private int priceList;
    private LocalDateTime startDate;
    private BigDecimal price;

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
