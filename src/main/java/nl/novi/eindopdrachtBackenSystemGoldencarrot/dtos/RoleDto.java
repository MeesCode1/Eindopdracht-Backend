package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos;

import jakarta.validation.constraints.NotBlank;

public class RoleDto {

    public Long id;
    @NotBlank
    public String roleName;
}