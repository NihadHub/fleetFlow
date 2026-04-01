package org.fleetflow.fleetflow.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LivraisonAssignDTO {
    @NotNull(message="L'ID du chauffeur est obligatoire")
    private Long chauffeurId;

    @NotNull(message = "L'ID du véhicule est obligatoire")
    private Long vehiculeId;
}
