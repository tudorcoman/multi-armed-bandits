package ro.unibuc.coman.licenta.reporting.web.dto;

import lombok.Builder;

@Builder
public record TreatmentStatisticsDTO (
        Long treatmentId,
        Long views,
        Long conversions
) {
}
