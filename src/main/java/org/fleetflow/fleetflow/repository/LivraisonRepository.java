package org.fleetflow.fleetflow.repository;
import org.fleetflow.fleetflow.entity.Livraison;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface LivraisonRepository extends JpaRepository<Livraison,Long> {
    Page<Livraison> findByStatut(StatutLivraison statut , Pageable pageable);
    Page<Livraison> findByClientClientId(Long id, Pageable pageable);
    Page<Livraison> findByChauffeurId(Long id, Pageable pageable);
    @Query ("select l from Livraison l where l.dateLivraison between :dateDebut and :dateFin")
    Page<Livraison> findLivraisonsEntreDeuxDates(@Param("dateDebut") LocalDate dateDebut,@Param("dateFin") LocalDate dateFin , Pageable pageable);
    @Query("select l from Livraison l where l.adresseDestination like %:ville%")
    Page<Livraison> findLivraisonsParVilleDestination(@Param("ville") String ville , Pageable pageable);

    long countByVehicule_Statut(StatutVehicule statut);
}
