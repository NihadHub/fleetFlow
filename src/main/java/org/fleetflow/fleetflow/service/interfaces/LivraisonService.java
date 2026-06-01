package org.fleetflow.fleetflow.service.interfaces;

import org.fleetflow.fleetflow.dto.LivraisonAssignDTO;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.entity.Client;
import org.fleetflow.fleetflow.entity.Livraison;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface LivraisonService {

    LivraisonDTO creerLivraison(LivraisonDTO dto);

    LivraisonDTO assignerChauffeurEtVehicule(Long livraisonId, LivraisonAssignDTO assignDTO);

    LivraisonDTO modifierStatut(Long livraisonId, StatutLivraison nouveauStatut);

    List<LivraisonDTO> listerTout();

    LivraisonDTO getLivraisonById(Long id);

    List<LivraisonDTO> listerParStatut(StatutLivraison statut);

    List<LivraisonDTO> listerParClient(Long clientId);

    List<LivraisonDTO> listerEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin);

    List<LivraisonDTO> listerParVilleDestination(String ville);
}
