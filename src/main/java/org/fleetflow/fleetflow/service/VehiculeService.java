package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {
    private VehiculeRepository vehiculeRepository;
    private VehiculeMapper vehiculeMapper;
    public VehiculeService(VehiculeRepository vehiculeRepository , VehiculeMapper vehiculeMapper){
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
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

    public List<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut){
        return vehiculeRepository.findVehiculeByStatut(statut).stream()
                .map(vehiculeMapper::toDTO).toList();
    }

    public List<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite){
        return vehiculeRepository.findVehiculeByCapaciteGreaterThan(capacite).stream()
                .map(vehiculeMapper::toDTO).toList();
    }
}
