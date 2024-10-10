package com.demo.bandits.reporting.web.dto;

import com.demo.bandits.reporting.infra.MappableDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import com.demo.bandits.reporting.entity.Treatment;

@Builder
public record TreatmentDTO(
        Long id,
        @NotEmpty(message = "Name is required") String name,
        @NotEmpty(message = "Asset is required") Long assetId
) implements MappableDTO<Treatment> {
    @Override
    public Treatment toEntity() {
        return Treatment.builder()
                .id(id)
                .name(name)
                .build();
    }
}
