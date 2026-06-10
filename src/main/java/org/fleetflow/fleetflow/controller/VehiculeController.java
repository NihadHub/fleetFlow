package org.fleetflow.fleetflow.controller;

import jakarta.validation.Valid;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.service.VehiculeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {
    private VehiculeService vehiculeService;


    public VehiculeController(VehiculeService vehiculeService){
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehiculeResponseDTO> addVehicule(@Valid @RequestBody VehiculeRequestDTO vehiculeDTO){
        VehiculeResponseDTO vehicule = vehiculeService.addVehicule(vehiculeDTO);
        return new ResponseEntity<>(vehicule ,HttpStatus.CREATED);
    }

    @PutMapping("/{vehiculeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VehiculeResponseDTO> updateVehicule(@PathVariable Long vehiculeId , @Valid @RequestBody VehiculeRequestDTO vehiculeDTO){
        return ResponseEntity.ok(vehiculeService.updateVehicule(vehiculeId ,vehiculeDTO));
    }

    @DeleteMapping("/{vehiculeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long vehiculeId){
        vehiculeService.deleteVehicule(vehiculeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<VehiculeResponseDTO>> getAllVehicule(Pageable pageable){
        return ResponseEntity.ok(vehiculeService.getAllVehicule(pageable));
    }

    @GetMapping("/statut/{statut}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<VehiculeResponseDTO>> getVehiculeByStatut(@PathVariable StatutVehicule statut, Pageable pageable){
        return ResponseEntity.ok(vehiculeService.getVehiculeByStatut(statut, pageable));
    }


    @GetMapping("/capacite/{capacite}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<VehiculeResponseDTO>> getVehiculeByCapaciteGreaterThan(@PathVariable double capacite, Pageable pageable){
        return ResponseEntity.ok(vehiculeService.getVehiculeByCapaciteGreaterThan(capacite, pageable));
    }
}
