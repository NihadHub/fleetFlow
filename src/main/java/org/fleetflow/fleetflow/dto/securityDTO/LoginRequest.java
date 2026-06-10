package org.fleetflow.fleetflow.dto.securityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "email obligatoire")
        @Email(message = "email invalid")
        String email,

        @NotBlank(message = "password obligatoire")
        String password

) {
}