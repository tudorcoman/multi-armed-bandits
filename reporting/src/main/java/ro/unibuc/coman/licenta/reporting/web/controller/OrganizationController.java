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
import ro.unibuc.coman.licenta.reporting.entity.Organization;
import ro.unibuc.coman.licenta.reporting.service.OrganizationService;
import ro.unibuc.coman.licenta.reporting.utils.DTOUtils;
import ro.unibuc.coman.licenta.reporting.web.dto.OrganizationDTO;

import java.util.List;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping
    public List<OrganizationDTO> getOrganizations() {
        final List<Organization> organizations = organizationService.getOrganizations();
        return organizations.stream()
                .map(DTOUtils::organizationEntityToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public OrganizationDTO getOrganization(@PathVariable Long id) {
        final Organization organization = organizationService.getOrganization(id);
        return DTOUtils.organizationEntityToDTO(organization);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationDTO createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        final Organization createdOrganization = organizationService.createOrganization(organizationDTO);
        return DTOUtils.organizationEntityToDTO(createdOrganization);
    }

    @PutMapping
    public OrganizationDTO updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        final Organization updatedOrganization = organizationService.updateOrganization(organizationDTO);
        return DTOUtils.organizationEntityToDTO(updatedOrganization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
    }
}
