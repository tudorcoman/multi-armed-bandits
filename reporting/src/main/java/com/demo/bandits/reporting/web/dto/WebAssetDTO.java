package com.demo.bandits.reporting.web.dto;


import com.demo.bandits.reporting.entity.WebAsset;
import com.demo.bandits.reporting.infra.MappableDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record WebAssetDTO(
        Long id,
        @NotEmpty(message = "Name is required")
        String name,
        String url,
        @NotEmpty(message = "Organization is required")
        Long organizationId,
        List<TreatmentDTO> treatments
) implements MappableDTO<WebAsset> {
    @Override
    public WebAsset toEntity() {
        return WebAsset.builder()
                .id(id)
                .name(name)
                .url(url)
                .treatments(Collections.emptyList())
                .build();
    }
}
