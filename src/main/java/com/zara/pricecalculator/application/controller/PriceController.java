package com.zara.pricecalculator.application.controller;

import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.application.service.price.PriceService;
import com.zara.pricecalculator.domain.model.request.PriceRequest;
import com.zara.pricecalculator.routes.Routes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Routes.BASE_ROUTER)
public class PriceController {
    @Autowired
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping(path = Routes.PRICES)
    public PriceDTO getPriceForProductAndBrand(@Valid @RequestBody PriceRequest priceRequest){
        return priceService.getApplicablePricesForProductAndBrand(priceRequest.getStartDate(), priceRequest.getProductId(), priceRequest.getBrandId());
    }
}
