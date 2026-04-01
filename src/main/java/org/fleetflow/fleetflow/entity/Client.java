package org.fleetflow.fleetflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String nom;
    private String telephone;
    private String ville;
    private String statut;

    public Client(String nom, String telephone, String ville, String statut) {
        this.nom = nom;
        this.telephone = telephone;
        this.ville = ville;
        this.statut = statut;
    }

    public Client() {
    }

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Livraison> livraisons;
}
