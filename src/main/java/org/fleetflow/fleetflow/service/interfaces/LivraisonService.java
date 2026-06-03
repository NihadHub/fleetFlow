package org.fleetflow.fleetflow.service.interfaces;

import org.fleetflow.fleetflow.dto.LivraisonAssignDTO;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.User;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface LivraisonService {

    LivraisonDTO creerLivraison(LivraisonDTO dto);

    LivraisonDTO assignerChauffeurEtVehicule(Long livraisonId, LivraisonAssignDTO assignDTO);

    LivraisonDTO modifierStatut(Long livraisonId, StatutLivraison nouveauStatut, User user);

    Page<LivraisonDTO> listerTout(Pageable pageable);

    LivraisonDTO getLivraisonById(Long id);

    Page<LivraisonDTO> listerParStatut(StatutLivraison statut , Pageable pageable);

    Page<LivraisonDTO> listerParClient(Long clientId , Pageable pageable);

    Page<LivraisonDTO> listerParChauffeur(Long chauffeurId, Pageable pageable);

    Page<LivraisonDTO> listerEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin , Pageable pageable);

    Page<LivraisonDTO> listerParVilleDestination(String ville , Pageable pageable);
}
