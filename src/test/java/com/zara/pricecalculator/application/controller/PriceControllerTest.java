package com.zara.pricecalculator.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.pricecalculator.application.dto.price.PriceDTO;
import com.zara.pricecalculator.application.manager.PriceManager;
import com.zara.pricecalculator.domain.model.request.PriceRequest;
import com.zara.pricecalculator.domain.model.response.PriceDTOResponse;
import com.zara.pricecalculator.infrastructure.persistence.PriceJpaRepository;
import com.zara.pricecalculator.routes.Routes;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest extends BaseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Mock
    private PriceManager priceManager;

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

        mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetApplicablePricesForProductAndBrand() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setStartDate(LocalDateTime.now());
        priceRequest.setProductId(1);
        priceRequest.setBrandId(1);

        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setBrandId(1);
        expectedPriceDTO.setPriceList(1);
        expectedPriceDTO.setProductId(1);
        expectedPriceDTO.setStartDate(LocalDateTime.now());
        expectedPriceDTO.setPrice(BigDecimal.TEN);

        PriceDTOResponse expectedResponse = new PriceDTOResponse(true, expectedPriceDTO);

        Mockito.when(priceManager.getApplicablePricesForProductAndBrand(Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(new PriceDTOResponse(true, expectedPriceDTO));

        ResponseEntity<PriceDTOResponse> responseEntity = priceController.getApplicablePricesForProductAndBrand(priceRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void givenValidProductAndBrand_whenGettingApplicablePrices_thenResponseStatusIsOkAndDataIsNull() throws Exception {
        LocalDateTime startDate = LocalDateTime.now();
        int productId = 1;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data").doesNotExist());
    }

    @Test
    public void test1_givenValidProductAndBrand_whenGettingApplicablePrice() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T10:00:00");
        int productId = 35455;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.brandId").value(1))
                .andExpect(jsonPath("$.data.priceList").value(2))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.price").value(25.45));
    }

    @Test
    public void test2_givenValidProductAndBrand_whenGettingApplicablePrice() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T16:00:00");
        int productId = 35455;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.brandId").value(1))
                .andExpect(jsonPath("$.data.priceList").value(3))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.price").value(30.50));
    }

    @Test
    public void test3_givenValidProductAndBrand_whenGettingApplicablePrice() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T21:00:00");
        int productId = 35455;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.brandId").value(1))
                .andExpect(jsonPath("$.data.priceList").value(3))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.price").value(30.50));
    }

    @Test
    public void test4_givenValidProductAndBrand_whenGettingApplicablePrice() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2020-06-15T10:00:00");
        int productId = 35455;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.brandId").value(1))
                .andExpect(jsonPath("$.data.priceList").value(4))
                .andExpect(jsonPath("$.data.productId").value(35455))
                .andExpect(jsonPath("$.data.price").value(38.95));
    }

    @Test
    public void test5_givenValidProductAndBrand_whenGettingApplicablePrice() throws Exception {
        LocalDateTime startDate = LocalDateTime.parse("2020-06-16T21:00:00");
        int productId = 35455;
        int brandId = 1;

        String requestBody = "{"
                + "\"startDate\":\"" + startDate.toString() + "\","
                + "\"productId\":" + productId + ","
                + "\"brandId\":" + brandId
                + "}";

        ResultActions resultActions = mockMvc.perform(post(Routes.BASE_ROUTER + Routes.GET_APPLICABLE_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}
