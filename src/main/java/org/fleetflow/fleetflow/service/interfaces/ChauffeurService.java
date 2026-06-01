package org.fleetflow.fleetflow.service.interfaces;
import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.mapper.ChauffeurMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ChauffeurService {
    ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO);

    ChauffeurDTO modifierChauffeur(Long id,ChauffeurDTO dto);

    void supprimerChauffeur(Long id);

    List<ChauffeurDTO> listerTous();

    List<ChauffeurDTO> listerDisponibles();

    ChauffeurDTO getChauffeurById(Long id);


}
