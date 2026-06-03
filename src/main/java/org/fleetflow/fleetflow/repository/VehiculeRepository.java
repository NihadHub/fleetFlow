package org.fleetflow.fleetflow.repository;

import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule , Long> {
    Page<Vehicule> findVehiculeByStatut(StatutVehicule statut , Pageable pageable);
    Page<Vehicule> findVehiculeByCapaciteGreaterThan(double capacite , Pageable pageable);

}

