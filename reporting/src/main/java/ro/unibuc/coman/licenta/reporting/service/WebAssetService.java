package ro.unibuc.coman.licenta.reporting.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.coman.licenta.reporting.entity.EventType;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;
import ro.unibuc.coman.licenta.reporting.infra.exceptions.NotFoundException;
import ro.unibuc.coman.licenta.reporting.infra.repository.WebAssetRepository;
import ro.unibuc.coman.licenta.reporting.web.dto.TreatmentStatisticsDTO;
import ro.unibuc.coman.licenta.reporting.web.dto.WebAssetDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WebAssetService {
    private final WebAssetRepository webAssetRepository;
    private final OrganizationService organizationService;

    @Transactional
    public WebAsset createWebAsset(WebAssetDTO webAssetDTO) {
        return webAssetRepository.save(dtoToEntityWithOrganization(webAssetDTO));
    }

    @Transactional
    public WebAsset getWebAsset(Long id) {
        return webAssetRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    @Transactional
    public List<WebAsset> getWebAssets() {
        return webAssetRepository.findAll();
    }

    @Transactional
    public void deleteWebAsset(Long id) {
        webAssetRepository.deleteById(id);
    }

    @Transactional
    public WebAsset updateWebAsset(WebAssetDTO webAssetDTO) {
        final WebAsset webAsset = getWebAsset(webAssetDTO.id());
        webAsset.setName(webAssetDTO.name());
        webAsset.setUrl(webAssetDTO.url());
        webAsset.setOrganization(organizationService.getOrganization(webAssetDTO.organizationId()));
        return webAssetRepository.save(webAsset);
    }

    private WebAsset dtoToEntityWithOrganization(WebAssetDTO webAssetDTO) {
        final WebAsset webAsset = webAssetDTO.toEntity();
        webAsset.setOrganization(organizationService.getOrganization(webAssetDTO.organizationId()));
        return webAsset;
    }
}
