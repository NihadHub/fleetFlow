package org.fleetflow.fleetflow.entity;
import org.fleetflow.fleetflow.enums.TypePermis;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chauffeurs")
@PrimaryKeyJoinColumn(name = "id")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Chauffeur extends User {

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePermis permisType;

    @Column(nullable = false)
    private Boolean disponible;
}