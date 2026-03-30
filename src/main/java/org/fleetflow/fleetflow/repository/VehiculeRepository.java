package org.fleetflow.fleetflow.repository;

import org.fleetflow.fleetflow.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule , Long> {
}
