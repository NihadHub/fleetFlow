package org.fleetflow.fleetflow.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.service.VehiculeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {
    private VehiculeService vehiculeService;


    public VehiculeController(VehiculeService vehiculeService){
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<VehiculeResponseDTO> addVehicule(@RequestBody VehiculeRequestDTO vehiculeDTO){
        VehiculeResponseDTO vehicule = vehiculeService.addVehicule(vehiculeDTO);
        return new ResponseEntity<>(vehicule ,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculeResponseDTO> updateClient(@PathVariable Long id , @RequestBody VehiculeRequestDTO vehiculeDTO){
        return ResponseEntity.ok(vehiculeService.updateVehicule(id , vehiculeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id){
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VehiculeResponseDTO>> getAllVehicule(){
        return ResponseEntity.ok(vehiculeService.getAllVehicule());
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<VehiculeResponseDTO>> getVehiculeByStatut(@PathVariable StatutVehicule statut){
        return ResponseEntity.ok(vehiculeService.getVehiculeByStatut(statut));
    }


    @GetMapping("/capacite/{capacite}")
    public ResponseEntity<List<VehiculeResponseDTO>> getVehiculeByCapaciteGreaterThan(@PathVariable double capacite){
        return ResponseEntity.ok(vehiculeService.getVehiculeByCapaciteGreaterThan(capacite));
    }
}
