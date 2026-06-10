package org.fleetflow.fleetflow.service.impl;

import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.dto.clientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.clientDTO.ClientResponseDTO;
import org.fleetflow.fleetflow.entity.Client;
import org.fleetflow.fleetflow.mapper.ClientMapper;
import org.fleetflow.fleetflow.repository.ClientRepository;
import org.fleetflow.fleetflow.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;



@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;


    @Override
    public ClientResponseDTO addClient(ClientRequestDTO clientDTO) {
        Client emailDejaExists =clientRepository.findClientByEmail(clientDTO.getEmail());
        if (emailDejaExists != null){
            throw new RuntimeException("Client deja exists.");
        }
        Client clientMapperEntity = clientMapper.toEntity(clientDTO);
        Client client = clientRepository.save(clientMapperEntity);
        return clientMapper.toResponseDTO(client);
    }

    @Override
    public ClientResponseDTO updateClient(Long clientId, ClientRequestDTO clientDTO) {
        Client existsClient = clientRepository.findById(clientId)
                .orElseThrow(()-> new RuntimeException("Client introuvable."));
        clientMapper.updateClientFromDto(clientDTO , existsClient);
        Client updateClient = clientRepository.save(existsClient);
        return clientMapper.toResponseDTO(updateClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Page<ClientResponseDTO> getAllClient(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(clientMapper::toResponseDTO);
    }
}
