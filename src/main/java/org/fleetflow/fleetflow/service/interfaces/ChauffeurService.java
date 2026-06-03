package org.fleetflow.fleetflow.service.interfaces;
import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.mapper.ChauffeurMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ChauffeurService {
    ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO);

    ChauffeurDTO modifierChauffeur(Long id,ChauffeurDTO dto);

    void supprimerChauffeur(Long id);

    Page<ChauffeurDTO> listerTous(Pageable pageable);

    Page<ChauffeurDTO> listerDisponibles(Pageable pageable);

    ChauffeurDTO getChauffeurById(Long id);
}
