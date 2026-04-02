package org.fleetflow.fleetflow.dto.clientDTO;
import lombok.Data;

@Data
public class ClientRequestDTO {
    private Long clientId;
    private String nom;
    private String telephone;
    private String ville;
    private String statut;
}
