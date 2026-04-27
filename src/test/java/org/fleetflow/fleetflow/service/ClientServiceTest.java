package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.clientDTO.*;
import org.fleetflow.fleetflow.entity.Client;
import org.fleetflow.fleetflow.mapper.ClientMapper;
import org.fleetflow.fleetflow.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock private ClientRepository repo;
    @Mock private ClientMapper mapper;
    @InjectMocks private ClientService service;

    @Test
    void addClient_Success() {
        String email = "test@test.com";
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setEmail(email);

        Client client = new Client();
        client.setEmail(email);

        ClientResponseDTO responseDTO = new ClientResponseDTO();
        responseDTO.setEmail(email);

        when(repo.findClientByEmail(email)).thenReturn(null);
        when(mapper.toEntity(dto)).thenReturn(client);
        when(repo.save(client)).thenReturn(client);
        when(mapper.toResponseDTO(client)).thenReturn(responseDTO);

        ClientResponseDTO result = service.addClient(dto);
        
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void addClient_Error_EmailExists() {
        String email = "test@test.com";
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setEmail(email);
        when(repo.findClientByEmail(email)).thenReturn(new Client());

        Exception ex = assertThrows(RuntimeException.class, () -> service.addClient(dto));
        assertEquals("Client deja exists.", ex.getMessage());
    }
}
