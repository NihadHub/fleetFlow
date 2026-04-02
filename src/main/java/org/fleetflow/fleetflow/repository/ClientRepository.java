package org.fleetflow.fleetflow.repository;

import org.fleetflow.fleetflow.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client , Long> {
}
