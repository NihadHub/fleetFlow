package org.fleetflow.fleetflow.service.impl;

import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.ChauffeurMapper;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.service.interfaces.ChauffeurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChauffeurServiceImpl implements ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;
    private final ChauffeurMapper chauffeurMapper;

    @Override
    public ChauffeurDTO ajouterChauffeur(ChauffeurDTO chauffeurDTO) {
        Chauffeur chauffeur = chauffeurMapper.toEntity(chauffeurDTO);
        Chauffeur saved = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDTO(saved);
    }

    @Override
    public ChauffeurDTO modifierChauffeur(Long id, ChauffeurDTO dto) {
        Chauffeur dejaExists = chauffeurRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Chauffeur non trouve : " + id)
        );
        chauffeurMapper.updateEntityFromDTO(dto , dejaExists);
        return chauffeurMapper.toDTO(chauffeurRepository.save(dejaExists));
    }

    @Override
    public void supprimerChauffeur(Long id) {
        if (!chauffeurRepository.existsById(id)) {
            throw new RuntimeException("Chauffeur non trouvé avec l'id : " + id);
        }
        chauffeurRepository.deleteById(id);
    }

    @Override
    public Page<ChauffeurDTO> listerTous(Pageable pageable) {
        Page<Chauffeur> chauffeurs = chauffeurRepository.findAll(pageable);
        return chauffeurs.map(chauffeurMapper::toDTO);
    }

    @Override
    public Page<ChauffeurDTO> listerDisponibles(Pageable pageable) {
        Page<Chauffeur> chauffeurs = chauffeurRepository.findByDisponibleTrue(pageable);
        return chauffeurs.map(chauffeurMapper::toDTO);
    }

    @Override
    public ChauffeurDTO getChauffeurById(Long id) {
        Chauffeur chauffeur = chauffeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chauffeur non trouvé avec l'id : " + id));
        return chauffeurMapper.toDTO(chauffeur);
    }
}
