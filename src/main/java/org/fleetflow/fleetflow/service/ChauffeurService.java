package org.fleetflow.fleetflow.service;
import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.mapper.ChauffeurMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class ChauffeurService {
    private final ChauffeurRepository chauffeurRepository;
    private final ChauffeurMapper chauffeurMapper;

    public ChauffeurDTO ajouterChauffeur(ChauffeurDTO dto){
        Chauffeur chauffeur = chauffeurMapper.toEntity(dto);
        Chauffeur saved = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDTO(saved);
    }

    public ChauffeurDTO modifierChauffeur(Long id,ChauffeurDTO dto){
        Chauffeur chauffeur=chauffeurRepository.findById(id).orElseThrow(() -> new RuntimeException("Chauffeur non trouvé avec l'id : " + id));
        chauffeurMapper.updateEntityFromDTO(dto,chauffeur);
        Chauffeur updated = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDTO(updated);
    }

    public void supprimerChauffeur(Long id) {
        if (!chauffeurRepository.existsById(id)) {
            throw new RuntimeException("Chauffeur non trouvé avec l'id : " + id);
        }
        chauffeurRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ChauffeurDTO> listerTous() {
        return chauffeurMapper.toDTOList(chauffeurRepository.findAll());
    }
    @Transactional(readOnly = true)
    public List<ChauffeurDTO> listerDisponibles() {
        return chauffeurMapper.toDTOList(chauffeurRepository.findByDisponibleTrue());
    }

    @Transactional(readOnly = true)
    public ChauffeurDTO getChauffeurById(Long id) {
        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chauffeur non trouvé avec l'id : " + id));
        return chauffeurMapper.toDTO(chauffeur);
    }





}
