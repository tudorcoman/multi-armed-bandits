package com.demo.bandits.reporting.web.dto;

import com.demo.bandits.reporting.entity.WebEvent;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import com.demo.bandits.reporting.entity.EventType;
import com.demo.bandits.reporting.infra.MappableDTO;

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
