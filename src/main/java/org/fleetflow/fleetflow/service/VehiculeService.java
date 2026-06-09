package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehiculeService {

    VehiculeResponseDTO addVehicule(VehiculeRequestDTO vehiculeDTO);

    VehiculeResponseDTO updateVehicule(Long vehiculeId , VehiculeRequestDTO vehiculeDTO);

    void deleteVehicule(Long vehiculeId);

    Page<VehiculeResponseDTO> getAllVehicule(Pageable pageable);

    Page<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut , Pageable pageable);

    Page<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite , Pageable pageable);
}
