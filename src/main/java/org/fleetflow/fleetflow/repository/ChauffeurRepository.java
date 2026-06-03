package org.fleetflow.fleetflow.repository;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChauffeurRepository extends JpaRepository<Chauffeur,Long>{
    Page<Chauffeur> findByDisponibleTrue(Pageable pageable);

    Page<Chauffeur> findAll(Pageable pageable);

}
