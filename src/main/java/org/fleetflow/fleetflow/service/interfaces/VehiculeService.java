package org.fleetflow.fleetflow.service.interfaces;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.LivraisonRepository;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface VehiculeService {

    VehiculeResponseDTO addVehicule(VehiculeRequestDTO vehiculeDTO);

    VehiculeResponseDTO updateVehicule(Long vehiculeId , VehiculeRequestDTO vehiculeDTO);

    void deleteVehicule(Long vehiculeId);

    Page<VehiculeResponseDTO> getAllVehicule(Pageable pageable);

    Page<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut , Pageable pageable);

    Page<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite , Pageable pageable);
}
