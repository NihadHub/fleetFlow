package org.fleetflow.fleetflow.entity;
import org.fleetflow.fleetflow.enums.TypePermis;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chauffeurs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long chauffeurId;

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