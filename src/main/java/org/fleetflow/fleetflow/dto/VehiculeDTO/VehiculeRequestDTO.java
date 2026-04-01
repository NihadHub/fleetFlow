package org.fleetflow.fleetflow.dto.VehiculeDTO;
import lombok.Data;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.enums.TypeVehicule;

@Data
public class VehiculeRequestDTO {
    private String matricule;
    private TypeVehicule type;
    private double capacite;
    private StatutVehicule statut;
}
