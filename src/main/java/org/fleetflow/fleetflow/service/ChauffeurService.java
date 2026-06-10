package org.fleetflow.fleetflow.service;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChauffeurService {
    ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO);

    ChauffeurDTO modifierChauffeur(Long id,ChauffeurDTO dto);

    void supprimerChauffeur(Long id);

    Page<ChauffeurDTO> listerTous(Pageable pageable);

    Page<ChauffeurDTO> listerDisponibles(Pageable pageable);

    ChauffeurDTO getChauffeurById(Long id);
}
