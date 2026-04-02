package org.fleetflow.fleetflow.entity;

import org.fleetflow.fleetflow.enums.StatutLivraison;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "livraisons")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Livraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long livraisonId;

    @Column(nullable = false)
    private LocalDate dateLivraison;

    @Column(nullable = false)
    private String adresseDepart;

    @Column(nullable = false)
    private String adresseDestination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutLivraison statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chauffeur_id")
    private Chauffeur chauffeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;
}