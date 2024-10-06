package ro.unibuc.coman.licenta.reporting.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.coman.licenta.reporting.entity.EventType;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;
import ro.unibuc.coman.licenta.reporting.infra.exceptions.NotFoundException;
import ro.unibuc.coman.licenta.reporting.infra.repository.WebEventRepository;
import ro.unibuc.coman.licenta.reporting.web.dto.TreatmentStatisticsDTO;
import ro.unibuc.coman.licenta.reporting.web.dto.WebEventDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebEventService {
    private final WebEventRepository webEventRepository;
    private final TreatmentService treatmentService;

    @Transactional
    public WebEvent createWebEvent(WebEventDTO webEventDTO) {
        return webEventRepository.save(dtoToEntityWithWebAsset(webEventDTO));
    }

    @Transactional
    public WebEvent getWebEvent(Long id) {
        return webEventRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public List<WebEvent> getWebEvents() {
        return webEventRepository.findAll();
    }

    @Transactional
    public List<WebEvent> getEventsForWebAsset(Long webAssetId) {
        return webEventRepository.findByAssetId(webAssetId);
    }

    @Transactional
    public void deleteWebEvent(Long id) {
        webEventRepository.deleteById(id);
    }

    @Transactional
    public void wipeEvents() {
        webEventRepository.deleteAll();
    }

    private WebEvent dtoToEntityWithWebAsset(WebEventDTO webEventDTO) {
        final WebEvent webEvent = webEventDTO.toEntity();
        final Treatment treatment = treatmentService.getTreatment(webEventDTO.treatmentId());
        webEvent.setTreatment(treatmentService.getTreatment(webEventDTO.treatmentId()));
        webEvent.setAsset(treatment.getWebAsset());
        return webEvent;
    }
}
