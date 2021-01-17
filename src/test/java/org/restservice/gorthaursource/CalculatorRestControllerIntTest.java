package org.restservice.gorthaursource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CalculatorRestControllerIntTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * Tests that json result received from url is equal to given result param
     *
     * @param url url to receive json response
     * @param result value to check an equality with json response
     * @throws Exception
     */
    public void jsonResponseResult(String url, Object result) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(APPLICATION_JSON))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(result));

    }

    /**
     * Tests that server responds with 404 code by given url
     *
     * @param url address to check
     * @throws Exception
     */
    public void isNotFound(String url) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addTest() throws Exception {
        jsonResponseResult("/add/1/2", 3);
        jsonResponseResult("/add/1/2/3", 6);
        jsonResponseResult("/add/1.1/2.2/3.3", 6.6);
    }

    @Test
    public void addTestWithoutRequiredParams() throws Exception {
        isNotFound("/add/1");
    }

    @Test
    public void multiplyTest() throws Exception {
        jsonResponseResult("/multiply/2/5", 10);
        jsonResponseResult("/multiply/4/2/3", 24);
        jsonResponseResult("/multiply/1.0/2.2/3.3", 7.26);
    }

    @Test
    public void multiplyTestWithoutRequiredParams() throws Exception {
        isNotFound("/multiply/1");
    }

    @Test
    public void divideTest() throws Exception {
        jsonResponseResult("/divide/2/5", 0.4);
        jsonResponseResult("/divide/4/2", 2);
        jsonResponseResult("/divide/6.6/0.3", 22);
        jsonResponseResult("/divide/10/3", BigDecimal.valueOf(10).divide(BigDecimal.valueOf(3), MathContext.DECIMAL128));
    }

    @Test
    public void divideTestWithoutRequiredParams() throws Exception {
        isNotFound("/divide/1");
    }

    @Test
    public void subtractTest() throws Exception {
        jsonResponseResult("/subtract/2/5", -3);
        jsonResponseResult("/subtract/4/2", 2);
        jsonResponseResult("/subtract/6.6/0.3", 6.3);
        jsonResponseResult("/subtract/10/3", 7);
        jsonResponseResult("/subtract/10/3/2", 5);
    }

    @Test
    public void subtractTestWithoutRequiredParams() throws Exception {
        isNotFound("/subtract/1");
    }
}