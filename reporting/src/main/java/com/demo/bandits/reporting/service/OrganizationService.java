package com.demo.bandits.reporting.service;

import com.demo.bandits.reporting.entity.Organization;
import com.demo.bandits.reporting.infra.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.demo.bandits.reporting.infra.repository.OrganizationRepository;
import com.demo.bandits.reporting.web.dto.OrganizationDTO;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization createOrganization(OrganizationDTO organizationDTO) {
        return organizationRepository.save(organizationDTO.toEntity());
    }

    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }

    public Organization updateOrganization(OrganizationDTO organizationDTO) {
        final Organization organization = getOrganization(organizationDTO.id());
        organization.setName(organizationDTO.name());
        organization.setAddress(organizationDTO.address());
        organization.setPhone(organizationDTO.phone());
        organization.setEmail(organizationDTO.email());
        return organizationRepository.save(organization);
    }
}
