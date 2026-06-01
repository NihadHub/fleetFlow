package org.fleetflow.fleetflow.service.interfaces;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.LivraisonRepository;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VehiculeService {

    VehiculeResponseDTO addVehicule(VehiculeRequestDTO vehiculeDTO);

    VehiculeResponseDTO updateVehicule(Long vehiculeId , VehiculeRequestDTO vehiculeDTO);

    void deleteVehicule(Long vehiculeId);

    List<VehiculeResponseDTO> getAllVehicule();

    List<VehiculeResponseDTO> getVehiculeByStatut(StatutVehicule statut);

    List<VehiculeResponseDTO> getVehiculeByCapaciteGreaterThan(double capacite);
}
