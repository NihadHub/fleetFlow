package org.fleetflow.fleetflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicule")
@Getter
@Setter
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehiculeId;
    private String matricule;
    private String type;
    private double capacite;
    private String statut;

    public Vehicule(String matricule, String type, double capacite, String statut) {
        this.matricule = matricule;
        this.type = type;
        this.capacite = capacite;
        this.statut = statut;
    }

    public Vehicule() {
    }

    @OneToMany
    @JsonIgnore
    private List<Livraison> livraison;
}
