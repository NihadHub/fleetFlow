package org.fleetflow.fleetflow.dto.ClientDTO;
import lombok.Data;

@Data
public class ClientResponseDTO {
    private Long clientId;
    private String nom;
    private String telephone;
    private String ville;
    private String statut;
}
