package ro.unibuc.coman.licenta.reporting.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ro.unibuc.coman.licenta.reporting.entity.Organization;
import ro.unibuc.coman.licenta.reporting.infra.MappableDTO;

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
