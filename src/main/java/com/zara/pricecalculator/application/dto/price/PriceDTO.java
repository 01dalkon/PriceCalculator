package com.zara.pricecalculator.application.dto.price;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceDTO {
    private int brandId;
    private int priceList;
    private int productId;
    private LocalDateTime startDate;
    private BigDecimal price;
}
