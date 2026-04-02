package org.fleetflow.fleetflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.enums.TypeVehicule;

import java.util.List;

@Entity
@Table(name = "vehicules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;

    @Enumerated(EnumType.STRING)
    private TypeVehicule type;

    private double capacite;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    @OneToMany(mappedBy = "vehicule")
    @JsonIgnore
    private List<Livraison> livraison;
}
