package ro.unibuc.coman.licenta.decisioning.web;

import org.springframework.core.convert.converter.Converter;
import ro.unibuc.coman.licenta.decisioning.entity.DecisioningStrategy;

public class StringToEnumConverter implements Converter<String, DecisioningStrategy> {
    @Override
    public DecisioningStrategy convert(String source) {
        return DecisioningStrategy.valueOf(source.toUpperCase());
    }
}
