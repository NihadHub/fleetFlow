package org.fleetflow.fleetflow.repository;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface
ChauffeurRepository extends JpaRepository<Chauffeur,Long>{
    List<Chauffeur> findByDisponibleTrue();
}
