package com.zara.pricecalculator.application.service.price;

import com.zara.pricecalculator.domain.model.price.Price;
import com.zara.pricecalculator.infrastructure.persistence.PriceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceJpaRepository priceJpaRepository;

    public List<Price>  getApplicablePricesForProductAndBrand(LocalDateTime startDate, LocalDateTime endDate, int productId, int brandId) {
        return priceJpaRepository
                .findByStartDateBetweenAndProductIdAndBrandIdOrderByPriorityDesc(
                        startDate, endDate, productId, brandId);
    }
}
