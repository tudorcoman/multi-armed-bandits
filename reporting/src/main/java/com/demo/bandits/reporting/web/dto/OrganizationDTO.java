package com.demo.bandits.reporting.web.dto;

import com.demo.bandits.reporting.entity.Organization;
import com.demo.bandits.reporting.infra.MappableDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record OrganizationDTO(
        Long id,
        @NotEmpty(message = "Name is required") String name,
        String address,
        String phone,
        String email
) implements MappableDTO<Organization> {
    @Override
    public Organization toEntity() {
        return Organization.builder()
                .id(id)
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }
}
