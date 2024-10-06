package ro.unibuc.coman.licenta.reporting.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.unibuc.coman.licenta.reporting.entity.Organization;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.infra.MappableDTO;

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
