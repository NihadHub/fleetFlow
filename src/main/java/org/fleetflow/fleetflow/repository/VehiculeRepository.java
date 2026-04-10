package org.fleetflow.fleetflow.repository;

import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule , Long> {
    List<Vehicule> findVehiculeByStatut(StatutVehicule statut);
    List<Vehicule> findVehiculeByCapaciteGreaterThan(double capacite);

}
