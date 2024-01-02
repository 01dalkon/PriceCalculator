package com.zara.pricecalculator.domain.model.price;

import com.zara.pricecalculator.domain.model.enums.Currency;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private int productId;
    private int priority;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Currency curr;
    private int brandId;

    public BigDecimal getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public int getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCurr(Currency curr) {
        this.curr = curr;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
