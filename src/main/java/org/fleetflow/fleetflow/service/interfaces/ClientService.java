package org.fleetflow.fleetflow.service.interfaces;

import org.fleetflow.fleetflow.dto.clientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.clientDTO.ClientResponseDTO;
import org.fleetflow.fleetflow.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ClientService {

    ClientResponseDTO addClient(ClientRequestDTO clientDTO);

    ClientResponseDTO updateClient(Long clientId , ClientRequestDTO clientDTO);

    void deleteClient(Long clientId);

    Page<ClientResponseDTO> getAllClient(Pageable pageable);
}
