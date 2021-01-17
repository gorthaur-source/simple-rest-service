package org.restservice.gorthaursource.controller;

import org.restservice.gorthaursource.model.Result;
import org.restservice.gorthaursource.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author kraken
 */
@RestController
public class CalculatorRestController {
    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorRestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RequestMapping({"/add/{a}/{b}", "/add/{a}/{b}/{c}"})
    public Result add(@PathVariable Map<String, String> operands) {
        final BigDecimal result = operands.values().stream().map(BigDecimal::new)
                .collect(Collectors.reducing(calculatorService::add)).get();

        return new Result<>(result);
    }

    @RequestMapping({"/multiply/{a}/{b}", "/multiply/{a}/{b}/{c}"})
    public Result multiply(@PathVariable Map<String, String> operands) {
        final BigDecimal result = operands.values().stream().map(BigDecimal::new)
                .collect(Collectors.reducing(calculatorService::multiply)).get();

        return new Result<>(result);
    }

    @RequestMapping("/divide/{a}/{b}")
    public Result divide(@PathVariable BigDecimal a, @PathVariable BigDecimal b) {
        final BigDecimal result = calculatorService.divide(a, b);

        return new Result<>(result);
    }

    @RequestMapping({"/subtract/{a}/{b}", "/subtract/{a}/{b}/{c}"})
    public Result subtract(@PathVariable Map<String, String> operands) {
        final Optional<BigDecimal> result = operands.values().stream().map(BigDecimal::new)
                .collect(Collectors.reducing(calculatorService::subtract));

        return new Result<>(result.get());
    }
}
