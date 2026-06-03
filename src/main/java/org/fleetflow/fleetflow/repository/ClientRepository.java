package org.fleetflow.fleetflow.repository;

import org.fleetflow.fleetflow.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client , Long> {
    Client findClientByEmail(String email);

    Page<Client> findAll(Pageable pageable);
}

