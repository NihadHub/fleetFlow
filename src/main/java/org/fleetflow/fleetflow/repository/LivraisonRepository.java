package org.fleetflow.fleetflow.repository;
import org.fleetflow.fleetflow.entity.Livraison;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
public interface LivraisonRepository extends JpaRepository<Livraison,Long> {
    List<Livraison> findLivraisonByStatut(StatutLivraison statut);
    List<Livraison> findLivraisonByClientId(Long id);
    @Query ("select l from Livraison l where l.dateLivraison between :dateDebut and :dateFin")
    List<Livraison> findLivraisonsEntreDeuxDates(@Param("dateDebut") LocalDate dateDebut,@Param("dateFin") LocalDate dateFin);
    @Query("select l from Livraison l where l.adresseDestination like %:ville%")
    List<Livraison> findLivraisonsParVilleDestination( @Param("ville") String ville);
}
