package org.restservice.gorthaursource.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Just to test caching
 */
@Component
public class TestService {
    private int i;

    @CalculationCache
    public int cachedMethod(BigDecimal a, BigDecimal b){
        return i++;
    }
}