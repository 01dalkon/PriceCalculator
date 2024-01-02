package com.zara.pricecalculator.application.controller;

import com.zara.pricecalculator.routes.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = Routes.BASE_ROUTER)
public class HealthController {

    @GetMapping(path = Routes.HEALTH)
    @ResponseStatus(HttpStatus.OK)
    public Map<Object, Object> health() {
        Map<Object, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK);

        return response;
    }
}
