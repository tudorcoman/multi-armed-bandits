package com.demo.bandits.reporting.service;

import com.demo.bandits.reporting.entity.WebEvent;
import com.demo.bandits.reporting.infra.exceptions.NotFoundException;
import com.demo.bandits.reporting.web.dto.WebEventDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.bandits.reporting.entity.Treatment;
import com.demo.bandits.reporting.infra.repository.WebEventRepository;

import java.util.List;

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
