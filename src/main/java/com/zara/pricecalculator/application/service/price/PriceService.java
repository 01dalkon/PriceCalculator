package com.zara.pricecalculator.application.service.price;

import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.domain.model.price.Price;
import com.zara.pricecalculator.infrastructure.persistence.PriceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceJpaRepository priceJpaRepository;

    public PriceDTO getApplicablePricesForProductAndBrand(LocalDateTime startDate, int productId, int brandId) {
        LocalDateTime endDate = calculateEndOfYear(startDate);

        List<Price> applicablePrices = priceJpaRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        startDate, endDate, productId, brandId);

        Price price = applicablePrices.stream().findFirst().orElse(null);

        return convertToPriceDTO(price);
    }

    private PriceDTO convertToPriceDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setProductId(price.getProductId());
        priceDTO.setBrandId(price.getBrandId());
        priceDTO.setPriceList(price.getPriceList());
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setPrice(price.getPrice());

        return priceDTO;
    }

    private LocalDateTime calculateEndOfYear(LocalDateTime startDate) {
        LocalDate lastDayOfYear = LocalDate.of(startDate.getYear(), 12, 31);

        return LocalDateTime.of(lastDayOfYear, LocalTime.MAX);
    }
}
