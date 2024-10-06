package ro.unibuc.coman.licenta.reporting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.coman.licenta.reporting.entity.EventType;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.service.ReportingService;
import ro.unibuc.coman.licenta.reporting.service.WebAssetService;
import ro.unibuc.coman.licenta.reporting.utils.DTOUtils;
import ro.unibuc.coman.licenta.reporting.web.dto.TreatmentStatisticsDTO;
import ro.unibuc.coman.licenta.reporting.web.dto.WebAssetDTO;

import java.util.List;

@RestController
@RequestMapping("/asset")
@AllArgsConstructor
public class WebAssetController {
    private final WebAssetService webAssetService;
    private final ReportingService reportingService;

    @GetMapping
    public List<WebAssetDTO> getWebAssets() {
        final List<WebAsset> webAssets = webAssetService.getWebAssets();
        return webAssets.stream()
                .map(DTOUtils::webAssetEntityToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public WebAssetDTO getWebAsset(@PathVariable Long id) {
        final WebAsset webAsset = webAssetService.getWebAsset(id);
        return DTOUtils.webAssetEntityToDTO(webAsset);
    }

    @GetMapping("/{id}/statistics")
    public List<TreatmentStatisticsDTO> getWebAssetStatistics(@PathVariable Long id) {
        return reportingService.getWebAssetStatistics(id, EventType.CLICK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebAssetDTO createWebAsset(@Valid @RequestBody WebAssetDTO webAssetDTO) {
        final WebAsset createdWebAsset = webAssetService.createWebAsset(webAssetDTO);
        return DTOUtils.webAssetEntityToDTO(createdWebAsset);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebAsset(@PathVariable Long id) {
        webAssetService.deleteWebAsset(id);
    }

    @PutMapping
    public WebAssetDTO updateWebAsset(@Valid @RequestBody WebAssetDTO webAssetDTO) {
        final WebAsset updatedWebAsset = webAssetService.updateWebAsset(webAssetDTO);
        return DTOUtils.webAssetEntityToDTO(updatedWebAsset);
    }

}
