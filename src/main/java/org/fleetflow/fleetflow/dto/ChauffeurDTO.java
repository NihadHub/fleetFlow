package org.fleetflow.fleetflow.dto;
import org.fleetflow.fleetflow.enums.TypePermis;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ChauffeurDTO {
    private long id;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotNull(message = "Le type de permis est obligatoire")
    private TypePermis permisType;

    @NotNull(message = "La disponibilité est obligatoire")
    private Boolean disponible;
}
