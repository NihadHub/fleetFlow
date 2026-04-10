package org.fleetflow.fleetflow.dto.vehiculeDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.enums.TypeVehicule;

@Data
public class VehiculeRequestDTO {
    @NotBlank(message = "Le matricule est obligatoire")
    private String matricule;

    @NotNull(message = "Le type de véhicule est obligatoire")
    private TypeVehicule type;

    @Positive(message = "La capacité doit être positive")
    private double capacite;

    private StatutVehicule statut;

}
