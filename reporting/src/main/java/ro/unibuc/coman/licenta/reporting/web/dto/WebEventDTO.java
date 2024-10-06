package ro.unibuc.coman.licenta.reporting.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.unibuc.coman.licenta.reporting.entity.EventType;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;
import ro.unibuc.coman.licenta.reporting.infra.MappableDTO;

import java.sql.Timestamp;
import java.time.Instant;

@Builder
public record WebEventDTO (
        Long id,
        @NotEmpty(message = "Type is required")
        String type,
        @NotEmpty(message = "Treatment is required")
        Long treatmentId) implements MappableDTO<WebEvent> {
    @Override
    public WebEvent toEntity() {
        return WebEvent.builder()
                .id(id)
                .timestamp(Timestamp.from(Instant.now()))
                .type(EventType.valueOf(type))
                .build();
    }
}
