package com.demo.bandits.reporting.utils;

import com.demo.bandits.reporting.entity.Organization;
import com.demo.bandits.reporting.entity.WebAsset;
import com.demo.bandits.reporting.entity.WebEvent;
import com.demo.bandits.reporting.web.dto.WebEventDTO;
import lombok.NoArgsConstructor;
import com.demo.bandits.reporting.entity.Treatment;
import com.demo.bandits.reporting.web.dto.OrganizationDTO;
import com.demo.bandits.reporting.web.dto.TreatmentDTO;
import com.demo.bandits.reporting.web.dto.WebAssetDTO;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DTOUtils {
    public static OrganizationDTO organizationEntityToDTO(Organization organization) {
        return OrganizationDTO.builder()
                .id(organization.getId())
                .name(organization.getName())
                .address(organization.getAddress())
                .phone(organization.getPhone())
                .email(organization.getEmail())
                .build();
    }

    public static WebAssetDTO webAssetEntityToDTO(WebAsset webAsset) {
        return WebAssetDTO.builder()
                .id(webAsset.getId())
                .name(webAsset.getName())
                .url(webAsset.getUrl())
                .organizationId(webAsset.getOrganization().getId())
                .treatments(webAsset.getTreatments().stream().map(DTOUtils::treatmentEntityToDTO).toList())
                .build();
    }

    public static WebEventDTO webEventEntityToDTO(WebEvent webEvent) {
        return WebEventDTO.builder()
                .id(webEvent.getId())
                .type(webEvent.getType().name())
                .treatmentId(webEvent.getTreatment().getId())
                .build();
    }

    public static TreatmentDTO treatmentEntityToDTO(Treatment treatment) {
        return TreatmentDTO.builder()
                .id(treatment.getId())
                .name(treatment.getName())
                .assetId(treatment.getWebAsset().getId())
                .build();
    }
}
