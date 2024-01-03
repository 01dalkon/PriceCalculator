package com.zara.pricecalculator.application.controller;

import com.zara.pricecalculator.domain.model.price.Price;
import com.zara.pricecalculator.domain.model.enums.Currency;
import com.zara.pricecalculator.infrastructure.persistence.PriceJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class BaseControllerTest {

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    public List<Price> createPrices() {
        List<Price> createdPrices = new ArrayList<>();

        LocalDateTime startDate1 = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Price price1 = generatePrice(startDate1, endDate1, 1, 1, 35455, 0, new BigDecimal("35.50"), Currency.EUR);
        createdPrices.add(priceJpaRepository.save(price1));

        LocalDateTime startDate2 = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2020, 6, 14, 18, 30, 0);
        Price price2 = generatePrice(startDate2, endDate2, 1, 2, 35455, 1, new BigDecimal("25.45"), Currency.EUR);
        createdPrices.add(priceJpaRepository.save(price2));

        LocalDateTime startDate3 = LocalDateTime.of(2020, 6, 15, 0, 0, 0);
        LocalDateTime endDate3 = LocalDateTime.of(2020, 6, 15, 11, 0, 0);
        Price price3 = generatePrice(startDate3, endDate3, 1, 3, 35455, 1, new BigDecimal("30.50"), Currency.EUR);
        createdPrices.add(priceJpaRepository.save(price3));

        LocalDateTime startDate4 = LocalDateTime.of(2020, 6, 15, 16, 0, 0);
        LocalDateTime endDate4 = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Price price4 = generatePrice(startDate4, endDate4, 1, 4, 35455, 1, new BigDecimal("38.95"), Currency.EUR);
        createdPrices.add(priceJpaRepository.save(price4));

        return createdPrices;
    }

    public Price generatePrice(
            LocalDateTime startDate, LocalDateTime endDate,
            int brandId, int priceList, int productId, int priority,
            BigDecimal price, Currency curr
    ) {
        Price generatedPrice = new Price();
        generatedPrice.setStartDate(startDate);
        generatedPrice.setEndDate(endDate);
        generatedPrice.setBrandId(brandId);
        generatedPrice.setPriceList(priceList);
        generatedPrice.setProductId(productId);
        generatedPrice.setPriority(priority);
        generatedPrice.setPrice(price);
        generatedPrice.setCurr(curr);

        generatedPrice.setProductId(productId);

        return generatedPrice;
    }
}
