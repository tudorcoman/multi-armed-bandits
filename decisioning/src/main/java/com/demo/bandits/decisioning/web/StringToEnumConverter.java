package com.demo.bandits.decisioning.web;

import org.springframework.core.convert.converter.Converter;
import com.demo.bandits.decisioning.entity.DecisioningStrategy;

public class StringToEnumConverter implements Converter<String, DecisioningStrategy> {
    @Override
    public DecisioningStrategy convert(String source) {
        return DecisioningStrategy.valueOf(source.toUpperCase());
    }
}
