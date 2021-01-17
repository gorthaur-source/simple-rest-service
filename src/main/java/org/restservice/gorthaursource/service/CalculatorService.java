package org.restservice.gorthaursource.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Service providing general calculation methods such as add, subtract, multiply, divide
 *
 */
@Service
public class CalculatorService {
    @CalculationCache
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @CalculationCache
    public BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    @CalculationCache
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    @CalculationCache
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, MathContext.DECIMAL128);
    }
}