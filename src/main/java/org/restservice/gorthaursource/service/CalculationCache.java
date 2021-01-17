package org.restservice.gorthaursource.service;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable(cacheNames="calculationCache", key = "#root.methodName + '(' + #a.hashCode() + '_' + #b.hashCode() + ')'")
public @interface CalculationCache {
}
