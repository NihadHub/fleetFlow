package org.fleetflow.fleetflow.controller;

import jakarta.validation.Valid;
import org.fleetflow.fleetflow.dto.clientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.clientDTO.ClientResponseDTO;
import org.fleetflow.fleetflow.service.interfaces.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<ClientResponseDTO> addClient(@Valid @RequestBody ClientRequestDTO clientDTO){
        ClientResponseDTO client = clientService.addClient(clientDTO);
        return new ResponseEntity<>(client , HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long clientId , @Valid @RequestBody ClientRequestDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(clientId ,clientDTO));
    }

    @DeleteMapping("/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable){
        return ResponseEntity.ok(clientService.getAllClient(pageable));
    }

}
