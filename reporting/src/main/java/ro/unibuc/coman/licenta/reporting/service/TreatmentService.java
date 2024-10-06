package ro.unibuc.coman.licenta.reporting.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.unibuc.coman.licenta.reporting.entity.Treatment;
import ro.unibuc.coman.licenta.reporting.entity.WebAsset;
import ro.unibuc.coman.licenta.reporting.infra.exceptions.NotFoundException;
import ro.unibuc.coman.licenta.reporting.infra.repository.TreatmentRepository;
import ro.unibuc.coman.licenta.reporting.web.dto.TreatmentDTO;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final WebAssetService webAssetService;

    public Treatment createTreatment(TreatmentDTO treatmentDTO) {
        final Treatment treatment = treatmentDTO.toEntity();
        final WebAsset webAsset = webAssetService.getWebAsset(treatmentDTO.assetId());
        treatment.setWebAsset(webAsset);
        return treatmentRepository.save(treatment);
    }

    public Treatment getTreatment(Long id) {
        return treatmentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Treatment> getTreatmentsForAsset(Long assetId) {
        final WebAsset webAsset = webAssetService.getWebAsset(assetId);
        return treatmentRepository.findAllByWebAsset(webAsset);
    }

    public List<Treatment> getTreatments() {
        return treatmentRepository.findAll();
    }

    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }

    public Treatment updateTreatment(TreatmentDTO treatmentDTO) {
        final Treatment treatment = getTreatment(treatmentDTO.id());
        treatment.setName(treatmentDTO.name());
        return treatmentRepository.save(treatment);
    }
}
