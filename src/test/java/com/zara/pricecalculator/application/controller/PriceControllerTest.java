package com.zara.pricecalculator.application.controller;

import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.application.service.price.PriceService;
import com.zara.pricecalculator.domain.model.price.Price;
import com.zara.pricecalculator.domain.model.request.PriceRequest;
import com.zara.pricecalculator.infrastructure.persistence.PriceJpaRepository;
import com.zara.pricecalculator.routes.Routes;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest extends BaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Test
    void invalidDateFormatGetPriceForProductAndBrand() throws Exception {
        String body = """
                {
                    "startDate": "2020-05-01"
                    "brandId": 1,
                    "productId": 1
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(Routes.BASE_ROUTER + Routes.PRICES).contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void getPriceForProductAndBrand() {
        PriceService priceService = Mockito.mock(PriceService.class);
        PriceController priceController = new PriceController(priceService);

        PriceRequest request = new PriceRequest();
        request.setStartDate(LocalDateTime.now());
        request.setProductId(1);
        request.setBrandId(2);

        PriceDTO expectedPrice = new PriceDTO();

        when(priceService.getApplicablePricesForProductAndBrand(request.getStartDate(), request.getProductId(), request.getBrandId())).thenReturn(expectedPrice);

        PriceDTO actualPrice = priceController.getPriceForProductAndBrand(request);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetApplicablePricesForProductAndBrand() {
        LocalDateTime startDate = LocalDateTime.now();
        int productId = 1;
        int brandId = 2;
        Price mockPrice = new Price(); // create a mock Price object with appropriate values

        when(priceJpaRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                any(LocalDateTime.class), any(LocalDateTime.class), anyInt(), anyInt()))
                .thenReturn(Collections.singletonList(mockPrice));

        PriceDTO result = priceService.getApplicablePricesForProductAndBrand(startDate, productId, brandId);

        verify(priceJpaRepository, times(1)).findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                any(LocalDateTime.class), any(LocalDateTime.class), anyInt(), anyInt());
    }

}
