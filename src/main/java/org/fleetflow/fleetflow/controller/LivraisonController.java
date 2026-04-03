package org.fleetflow.fleetflow.controller;
import org.fleetflow.fleetflow.dto.LivraisonAssignDTO;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.service.LivraisonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
@RequiredArgsConstructor
@Tag(name = "Livraisons", description = "Gestion des livraisons")
public class LivraisonController {

    private final LivraisonService livraisonService;

    @PostMapping
    @Operation(summary = "Créer une livraison")
    public ResponseEntity<LivraisonDTO> creerLivraison(@Valid @RequestBody LivraisonDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livraisonService.creerLivraison(dto));
    }

    @PutMapping("/{id}/assigner")
    @Operation(summary = "Assigner un chauffeur et un véhicule à une livraison")
    public ResponseEntity<LivraisonDTO> assignerChauffeurEtVehicule(
            @PathVariable Long id,
            @Valid @RequestBody LivraisonAssignDTO assignDTO) {
        return ResponseEntity.ok(livraisonService.assignerChauffeurEtVehicule(id, assignDTO));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Modifier le statut d'une livraison")
    public ResponseEntity<LivraisonDTO> modifierStatut(
            @PathVariable Long id,
            @RequestParam StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.modifierStatut(id, statut));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les livraisons")
    public ResponseEntity<List<LivraisonDTO>> listerTout() {
        return ResponseEntity.ok(livraisonService.listerTout());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une livraison par ID")
    public ResponseEntity<LivraisonDTO> getLivraison(@PathVariable Long id) {
        return ResponseEntity.ok(livraisonService.getLivraisonById(id));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Lister les livraisons par statut")
    public ResponseEntity<List<LivraisonDTO>> listerParStatut(@PathVariable StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.listerParStatut(statut));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Lister les livraisons par client")
    public ResponseEntity<List<LivraisonDTO>> listerParClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(livraisonService.listerParClient(clientId));
    }

    @GetMapping("/dates")
    @Operation(summary = "Lister les livraisons entre deux dates")
    public ResponseEntity<List<LivraisonDTO>> listerEntreDeuxDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return ResponseEntity.ok(livraisonService.listerEntreDeuxDates(dateDebut, dateFin));
    }

    @GetMapping("/ville")
    @Operation(summary = "Lister les livraisons par ville de destination")
    public ResponseEntity<List<LivraisonDTO>> listerParVille(@RequestParam String ville) {
        return ResponseEntity.ok(livraisonService.listerParVilleDestination(ville));
    }
}
