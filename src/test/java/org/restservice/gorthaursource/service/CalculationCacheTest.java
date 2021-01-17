package org.restservice.gorthaursource.service;

import org.restservice.gorthaursource.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CalculationCacheTest {
    @Autowired
    private TestService testService;

    @Test
    public void cachedMethodMustAlwaysReturnTheSameValue() {
        int res1 = testService.cachedMethod(new BigDecimal(1), new BigDecimal(2));
        int res2 = testService.cachedMethod(new BigDecimal(1), new BigDecimal(2));
        assertEquals(res1, res2);
    }

    @Test
    public void whenGivenDifferentParamsCachedMethodMustAlwaysReturnDifferentResult() {
        int res1 = testService.cachedMethod(new BigDecimal(2), new BigDecimal(3));
        int res2 = testService.cachedMethod(new BigDecimal(2), new BigDecimal(5));
        assertNotEquals(res1, res2);
    }
}
