package com.zara.pricecalculator.infrastructure.persistence;

import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.domain.model.price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<Price, Long> {
    List<Price> findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDate, LocalDateTime endDate, int productId, int brandId
    );

    @Query(value =
            "SELECT * " +
            "FROM Prices p " +
            "WHERE (p.start_date BETWEEN :startDate AND :endDate) " +
            "AND (p.product_id = :productId) " +
            "AND (p.brand_id = :brandId) " +
            "ORDER BY p.priority DESC LIMIT 1",
            nativeQuery = true)
    List<Price> findByStartDateBetweenAndProductIdAndBrandIdOrderByPriorityDesc(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("productId") int productId,
            @Param("brandId") int brandId
    );
}
