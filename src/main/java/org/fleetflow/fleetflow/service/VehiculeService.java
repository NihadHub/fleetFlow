package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.LivraisonRepository;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;
    private final LivraisonRepository livraisonRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository, VehiculeMapper vehiculeMapper, LivraisonRepository livraisonRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
        this.livraisonRepository = livraisonRepository;
    }

    public VehiculeResponseDTO addVehicule(VehiculeRequestDTO vehiculeDTO){
        Vehicule vehiculeMapperEntity = vehiculeMapper.toEntity(vehiculeDTO);
        Vehicule vehicule = vehiculeRepository.save(vehiculeMapperEntity);
        return vehiculeMapper.toDTO(vehicule);
    }

    public VehiculeResponseDTO updateVehicule(Long vehiculeId , VehiculeRequestDTO vehiculeDTO){
        Vehicule existsVehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(()-> new RuntimeException("Vehicule introuvable."));
        vehiculeMapper.updateVehiculeFromDto(vehiculeDTO , existsVehicule);
        Vehicule updateVehicule = vehiculeRepository.save(existsVehicule);
        return vehiculeMapper.toDTO(updateVehicule);
    }

    public void deleteVehicule(Long vehiculeId){
        vehiculeRepository.deleteById(vehiculeId);
    }

    public List<VehiculeResponseDTO> getAllVehicule(){
        return vehiculeRepository.findAll().stream()
                .map(vehiculeMapper::toDTO).toList();
    }

    public List<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut) {
        return vehiculeRepository.findVehiculeByStatut(statut).stream().map(v -> {
            long countVehicule = livraisonRepository.countByVehicule_VehiculeId(statut);
            VehiculeResponseDTO dto = vehiculeMapper.toDTO(v);
            dto.setCountVehicule(countVehicule);
            return dto;
        }).toList();
    }

    public List<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite){
        return vehiculeRepository.findVehiculeByCapaciteGreaterThan(capacite).stream()
                .map(vehiculeMapper::toDTO).toList();
    }
}
