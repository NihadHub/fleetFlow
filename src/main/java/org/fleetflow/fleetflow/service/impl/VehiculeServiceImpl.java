package org.fleetflow.fleetflow.service.impl;

import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.fleetflow.fleetflow.service.VehiculeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public VehiculeResponseDTO addVehicule(VehiculeRequestDTO vehiculeDTO) {
        Vehicule vehiculeMapperEntity = vehiculeMapper.toEntity(vehiculeDTO);
        Vehicule vehicule = vehiculeRepository.save(vehiculeMapperEntity);
        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    public VehiculeResponseDTO updateVehicule(Long vehiculeId, VehiculeRequestDTO vehiculeDTO) {
        Vehicule existsVehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(()-> new RuntimeException("Vehicule introuvable."));
        vehiculeMapper.updateVehiculeFromDto(vehiculeDTO , existsVehicule);
        Vehicule updateVehicule = vehiculeRepository.save(existsVehicule);
        return vehiculeMapper.toDTO(updateVehicule);
    }

    @Override
    public void deleteVehicule(Long vehiculeId) {
        vehiculeRepository.deleteById(vehiculeId);
    }

    @Override
    public Page<VehiculeResponseDTO> getAllVehicule(Pageable pageable) {
        Page<Vehicule> vehicules = vehiculeRepository.findAll(pageable);
        return vehicules.map(vehiculeMapper::toDTO);
    }

    @Override
    public Page<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut, Pageable pageable) {
        Page<Vehicule> vehicules = vehiculeRepository.findVehiculeByStatut(statut , pageable);
        return vehicules.map(vehiculeMapper::toDTO);
    }

    @Override
    public Page<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite, Pageable pageable) {
        Page<Vehicule> vehicules = vehiculeRepository.findVehiculeByCapaciteGreaterThan(capacite , pageable);
        return vehicules.map(vehiculeMapper::toDTO);

    }
}
