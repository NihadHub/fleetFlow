package org.fleetflow.fleetflow.controller;

import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.service.ChauffeurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chauffeurs")
@RequiredArgsConstructor
@Tag(name = "Chauffeurs", description = "Gestion des chauffeurs")
public class ChauffeurController {

    private final ChauffeurService chauffeurService;

    @PostMapping
    @Operation(summary = "Ajouter un chauffeur")
        @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChauffeurDTO> ajouterChauffeur(@Valid @RequestBody ChauffeurDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chauffeurService.ajouterChauffeur(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un chauffeur")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChauffeurDTO> modifierChauffeur(@PathVariable Long id, @Valid @RequestBody ChauffeurDTO dto) {
        return ResponseEntity.ok(chauffeurService.modifierChauffeur(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un chauffeur")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerChauffeur(@PathVariable Long id) {
        chauffeurService.supprimerChauffeur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les chauffeurs")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<ChauffeurDTO>> listerTous(Pageable pageable) {
        return ResponseEntity.ok(chauffeurService.listerTous(pageable));
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Lister les chauffeurs disponibles")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<ChauffeurDTO>> listerDisponibles(Pageable pageable) {
        return ResponseEntity.ok(chauffeurService.listerDisponibles(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un chauffeur par ID")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<ChauffeurDTO> getChauffeur(@PathVariable Long id) {
        return ResponseEntity.ok(chauffeurService.getChauffeurById(id));
    }
}