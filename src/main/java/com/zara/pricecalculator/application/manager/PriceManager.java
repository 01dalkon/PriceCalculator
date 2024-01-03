package com.zara.pricecalculator.application.manager;

import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.application.service.price.PriceService;
import com.zara.pricecalculator.domain.model.price.Price;
import com.zara.pricecalculator.domain.model.response.PriceDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class PriceManager {
    @Autowired
    private final PriceService priceService;

    public PriceManager(PriceService priceService) {
        this.priceService = priceService;
    }

    public PriceDTOResponse getApplicablePricesForProductAndBrand(LocalDateTime startDate, int productId, int brandId) {
        LocalDateTime endDate = calculateEndOfYear(startDate);

        List<Price> applicablePrices = priceService
                .getApplicablePricesForProductAndBrand(
                        startDate, endDate, productId, brandId);

        Price price = applicablePrices.stream().findFirst().orElse(null);

        PriceDTO response = convertToPriceDTO(price);

        return new PriceDTOResponse(true, response);
    }

    private PriceDTO convertToPriceDTO(Price price) {
        if (price == null) {
            return null;
        }

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
