package com.zara.pricecalculator.infrastructure.persistence;

import com.zara.pricecalculator.domain.model.price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<Price, Long> {
    List<Price> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDate, LocalDateTime endDate, int productId, int brandId);
}
