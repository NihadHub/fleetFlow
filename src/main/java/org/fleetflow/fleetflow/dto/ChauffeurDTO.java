package org.fleetflow.fleetflow.dto;
import org.fleetflow.fleetflow.enums.TypePermis;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ChauffeurDTO {
    private Long id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;

    @Email(message = "Email invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotNull(message = "Le type de permis est obligatoire")
    private TypePermis permisType;

    @NotNull(message = "La disponibilité est obligatoire")
    private Boolean disponible;
}
