package org.fleetflow.fleetflow.dto;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LivraisonDTO {
    private Long livraisonId;
    @NotNull(message = "La date de livraison est obligatoire")
    private LocalDate dateLivraison;

    @NotBlank(message = "L'adresse de départ est obligatoire")
    private String adresseDepart;

    @NotBlank(message = "L'adresse de destination est obligatoire")
    private String adresseDestination;

    private StatutLivraison statut;
    @NotNull(message ="L'id client est obligatoire")
    private Long clientId;
    private String clientNom;

    private Long chauffeurId;
    private String chauffeurNom;

    private Long vehiculeId;
    private String vehiculeMatricule;
}
