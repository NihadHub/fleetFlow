package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.mapper.ClientMapper;
import org.fleetflow.fleetflow.dto.clientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.clientDTO.ClientResponseDTO;
import org.fleetflow.fleetflow.entity.Client;
import org.fleetflow.fleetflow.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    public ClientService(ClientRepository clientRepository , ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientResponseDTO addClient(ClientRequestDTO clientDTO){
        Client clientMapperEntity = clientMapper.toEntity(clientDTO);
        Client client = clientRepository.save(clientMapperEntity);
        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO updateClient(Long id , ClientRequestDTO clientDTO){
        Client existsClient = clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Client introuvable."));
        clientMapper.updateClientFromDto(clientDTO , existsClient);
        Client updateClient = clientRepository.save(existsClient);
        return clientMapper.toResponseDTO(updateClient);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

    public List<ClientResponseDTO> getAllClient(){
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponseDTO).toList();
    }
}
