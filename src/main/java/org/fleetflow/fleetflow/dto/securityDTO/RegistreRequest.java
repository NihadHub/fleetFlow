package org.fleetflow.fleetflow.dto.securityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.fleetflow.fleetflow.enums.RoleUser;
import org.fleetflow.fleetflow.enums.TypePermis;

public record RegistreRequest(

        @NotBlank(message = "user name obligatoire")
        String username,

        @NotBlank(message = "email obligatoire")
        @Email(message = "email invalid")
        String email,

        @NotNull(message = "role obligatoire")
        RoleUser role,

        @NotBlank(message = "password obligatoire")
        String password,

        String telephone,

        TypePermis permisType

) {
}
