package org.fleetflow.fleetflow.dto.vehiculeDTO;
import lombok.Data;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.enums.TypeVehicule;

@Data
public class VehiculeResponseDTO {
    private Long vehiculeId;
    private String matricule;
    private TypeVehicule type;
    private double capacite;
    private StatutVehicule statut;
    private long countVehicule;
}
