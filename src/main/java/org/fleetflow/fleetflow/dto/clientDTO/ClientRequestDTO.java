package org.fleetflow.fleetflow.dto.clientDTO;
import lombok.Data;

@Data
public class ClientRequestDTO {
    private String nom;
    private String telephone;
    private String ville;
    private String statut;
}
