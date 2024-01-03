package com.zara.pricecalculator.application.controller;

import com.zara.pricecalculator.application.manager.PriceManager;
import com.zara.pricecalculator.domain.model.request.PriceRequest;
import com.zara.pricecalculator.domain.model.response.PriceDTOResponse;
import com.zara.pricecalculator.routes.Routes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = Routes.BASE_ROUTER)
public class PriceController {
    @Autowired
    private final PriceManager priceManager;

    public PriceController(PriceManager priceManager) {
        this.priceManager = priceManager;
    }

    @PostMapping(path = Routes.GET_APPLICABLE_PRICES, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDTOResponse> getApplicablePricesForProductAndBrand(@Valid @RequestBody PriceRequest priceRequest) {
        try {
            LocalDateTime startDate = priceRequest.getStartDate();
            int productId = priceRequest.getProductId();
            int brandId = priceRequest.getBrandId();

            PriceDTOResponse priceDTOResponse = priceManager.getApplicablePricesForProductAndBrand(startDate, productId, brandId);

            if (priceDTOResponse != null && priceDTOResponse.getStatus()) {
                return ResponseEntity.ok(priceDTOResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(priceDTOResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PriceDTOResponse(false, null));
        }
    }
}
