package com.zara.pricecalculator.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthControllerTest {

    @Test
    void healthEndpointShouldReturnStatusOk() {
        HealthController healthController = new HealthController();

        Map<Object, Object> result = healthController.health();

        assertEquals(HttpStatus.OK, result.get("status"));
    }
}
