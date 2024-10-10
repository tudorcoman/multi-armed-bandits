package com.demo.bandits.reporting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.bandits.reporting.entity.Treatment;
import com.demo.bandits.reporting.service.TreatmentService;
import com.demo.bandits.reporting.utils.DTOUtils;
import com.demo.bandits.reporting.web.dto.TreatmentDTO;

import java.util.List;

@RestController
@RequestMapping("/treatment")
@AllArgsConstructor
public class TreatmentController {
    private final TreatmentService treatmentService;

    @GetMapping
    public List<TreatmentDTO> getTreatments() {
        final List<Treatment> treatments = treatmentService.getTreatments();
        return treatmentEntityToDTO(treatments);
    }

    @GetMapping("/asset/{id}")
    public List<TreatmentDTO> getTreatmentForAsset(@PathVariable Long id) {
        final List<Treatment> treatments = treatmentService.getTreatmentsForAsset(id);
        return treatmentEntityToDTO(treatments);
    }

    @GetMapping("/{id}")
    public TreatmentDTO getTreatment(@PathVariable Long id) {
        final Treatment treatment = treatmentService.getTreatment(id);
        return DTOUtils.treatmentEntityToDTO(treatment);
    }

    @PostMapping
    public TreatmentDTO createTreatment(@Valid @RequestBody TreatmentDTO treatmentDTO) {
        final Treatment createdTreatment = treatmentService.createTreatment(treatmentDTO);
        return DTOUtils.treatmentEntityToDTO(createdTreatment);
    }

    @PutMapping
    public TreatmentDTO updateTreatment(@Valid @RequestBody TreatmentDTO treatmentDTO) {
        final Treatment updatedTreatment = treatmentService.updateTreatment(treatmentDTO);
        return DTOUtils.treatmentEntityToDTO(updatedTreatment);
    }

    @DeleteMapping("/{id}")
    public void deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
    }

    private List<TreatmentDTO> treatmentEntityToDTO(List<Treatment> treatments) {
        return treatments.stream()
                .map(DTOUtils::treatmentEntityToDTO)
                .toList();
    }
}
