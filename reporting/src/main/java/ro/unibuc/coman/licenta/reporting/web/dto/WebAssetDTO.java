package ro.unibuc.coman.licenta.reporting.web.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.infra.MappableDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
