package org.fleetflow.fleetflow.controller;
import org.fleetflow.fleetflow.dto.LivraisonAssignDTO;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.User;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.service.interfaces.LivraisonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
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
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<LivraisonDTO> creerLivraison(@Valid @RequestBody LivraisonDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livraisonService.creerLivraison(dto));
    }

    @PutMapping("/{id}/assigner")
    @Operation(summary = "Assigner un chauffeur et un véhicule à une livraison")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<LivraisonDTO> assignerChauffeurEtVehicule(
            @PathVariable Long id,
            @Valid @RequestBody LivraisonAssignDTO assignDTO) {
        return ResponseEntity.ok(livraisonService.assignerChauffeurEtVehicule(id, assignDTO));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Modifier le statut d'une livraison")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CHAUFFEUR')")
    public ResponseEntity<LivraisonDTO> modifierStatut(
            @PathVariable Long id,
            @RequestParam StatutLivraison statut,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(livraisonService.modifierStatut(id, statut, user));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les livraisons")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<LivraisonDTO>> listerTout(Pageable pageable) {
        return ResponseEntity.ok(livraisonService.listerTout(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une livraison par ID")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<LivraisonDTO> getLivraison(@PathVariable Long id) {
        return ResponseEntity.ok(livraisonService.getLivraisonById(id));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Lister les livraisons par statut")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<LivraisonDTO>> listerParStatut(@PathVariable StatutLivraison statut, Pageable pageable) {
        return ResponseEntity.ok(livraisonService.listerParStatut(statut, pageable));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Lister les livraisons par client")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<LivraisonDTO>> listerParClient(@PathVariable Long clientId, Pageable pageable) {
        return ResponseEntity.ok(livraisonService.listerParClient(clientId, pageable));
    }

    @GetMapping("/dates")
    @Operation(summary = "Lister les livraisons entre deux dates")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<LivraisonDTO>> listerEntreDeuxDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            Pageable pageable) {
        return ResponseEntity.ok(livraisonService.listerEntreDeuxDates(dateDebut, dateFin, pageable));
    }

    @GetMapping("/ville")
    @Operation(summary = "Lister les livraisons par ville de destination")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MANAGER')")
    public ResponseEntity<Page<LivraisonDTO>> listerParVille(@RequestParam String ville, Pageable pageable) {
        return ResponseEntity.ok(livraisonService.listerParVilleDestination(ville, pageable));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('CHAUFFEUR')")
    @Operation(summary = "Lister les livraisons du chauffeur connecté")
    public ResponseEntity<Page<LivraisonDTO>> MonLivraison(@AuthenticationPrincipal User user, Pageable pageable){
        return ResponseEntity.ok(livraisonService.listerParChauffeur(user.getId(), pageable));
    }
}
