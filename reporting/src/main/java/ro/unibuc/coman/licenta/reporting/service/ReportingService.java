package ro.unibuc.coman.licenta.reporting.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.coman.licenta.reporting.entity.EventType;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;
import ro.unibuc.coman.licenta.reporting.web.dto.TreatmentStatisticsDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportingService {
    private final WebEventService webEventService;
    private final WebAssetService webAssetService;

    @Transactional
    public List<TreatmentStatisticsDTO> getWebAssetStatistics(Long id, EventType campaignTarget) {
        final WebAsset webAsset = webAssetService.getWebAsset(id);
        final List<WebEvent> events = webEventService.getEventsForWebAsset(webAsset.getId());
        final List<Treatment> treatments = webAsset.getTreatments();

        final Map<Treatment, List<WebEvent>> treatmentEvents = events.stream()
                .collect(Collectors.groupingBy(WebEvent::getTreatment));

        return treatments.stream()
                .map(treatment -> getStatistics(treatment, treatmentEvents.getOrDefault(treatment, List.of()), campaignTarget))
                .collect(Collectors.toList());
    }

    private TreatmentStatisticsDTO getStatistics(Treatment treatment, List<WebEvent> treatmentEvents, EventType campaignTarget) {
        final Long conversions = treatmentEvents.stream()
                .filter(event -> event.getType().equals(campaignTarget))
                .count();

        final Long views = treatmentEvents.stream()
                .filter(event -> event.getType().equals(EventType.PAGE_VIEW))
                .count();

        return TreatmentStatisticsDTO.builder()
                .treatmentId(treatment.getId())
                .conversions(conversions)
                .views(views)
                .build();
    }
}
