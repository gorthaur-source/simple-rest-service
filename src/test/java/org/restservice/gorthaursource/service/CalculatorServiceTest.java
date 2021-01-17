package org.restservice.gorthaursource.service;

import org.restservice.gorthaursource.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CalculatorServiceTest {
    @Autowired
    private CalculatorService calculatorService;

    private BigDecimal one = valueOf(1);
    private BigDecimal two = valueOf(2);
    private BigDecimal three = valueOf(3);

    @Test
    public void addTest() {
        assertEquals(calculatorService.add(one, two), three);
    }

    @Test
    public void subtractTest() {
        assertEquals(calculatorService.subtract(three, one), two);
        assertEquals(calculatorService.subtract(one, two), valueOf(-1));
    }

    @Test
    public void multiplyTest() {
        assertEquals(calculatorService.multiply(three, two), valueOf(6));
    }

    @Test
    public void divideTest() {
        assertEquals(calculatorService.divide(three, two), valueOf(1.5));
        assertEquals(calculatorService.divide(one, two), valueOf(0.5));
        assertEquals(calculatorService.divide(three, one), valueOf(3));
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        calculatorService.divide(three, valueOf(0));
    }
}