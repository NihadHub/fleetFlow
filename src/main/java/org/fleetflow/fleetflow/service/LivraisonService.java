package org.fleetflow.fleetflow.service;
import lombok.RequiredArgsConstructor;
import org.fleetflow.fleetflow.dto.LivraisonAssignDTO;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.entity.Client;
import org.fleetflow.fleetflow.entity.Livraison;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutLivraison;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.LivraisonMapper;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.repository.ClientRepository;
import org.fleetflow.fleetflow.repository.LivraisonRepository;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LivraisonService {
    private final LivraisonRepository livraisonRepository;
    private final ClientRepository clientRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LivraisonMapper livraisonMapper;

    public LivraisonDTO creerLivraison(LivraisonDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException(
                        "Client non trouvé avec l'id : " + dto.getClientId()));

        Livraison livraison = Livraison.builder()
                .dateLivraison(dto.getDateLivraison())
                .adresseDepart(dto.getAdresseDepart())
                .adresseDestination(dto.getAdresseDestination())
                .statut(StatutLivraison.EN_ATTENTE)
                .client(client)
                .build();

        Livraison saved = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(saved);
    }

    public LivraisonDTO assignerChauffeurEtVehicule(Long livraisonId, LivraisonAssignDTO assignDTO) {
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException(
                        "Livraison non trouvée avec l'id : " + livraisonId));


        Chauffeur chauffeur = chauffeurRepository.findById(assignDTO.getChauffeurId())
                .orElseThrow(() -> new RuntimeException(
                        "Chauffeur non trouvé avec l'id : " + assignDTO.getChauffeurId()));

        if (!chauffeur.getDisponible()) {
            throw new IllegalStateException("Le chauffeur " + chauffeur.getNom() + " n'est pas disponible");
        }

        Vehicule vehicule = vehiculeRepository.findById(assignDTO.getVehiculeId())
                .orElseThrow(() -> new RuntimeException(
                        "Véhicule non trouvé avec l'id : " + assignDTO.getVehiculeId()));

        if (vehicule.getStatut() != StatutVehicule.DISPONIBLE) {
            throw new IllegalStateException(
                    "Le véhicule " + vehicule.getMatricule() + " n'est pas disponible (statut: " + vehicule.getStatut() + ")");
        }


        livraison.setChauffeur(chauffeur);
        livraison.setVehicule(vehicule);
        livraison.setStatut(StatutLivraison.EN_COURS);


        chauffeur.setDisponible(false);
        vehicule.setStatut(StatutVehicule.EN_LIVRAISON);

        chauffeurRepository.save(chauffeur);
        vehiculeRepository.save(vehicule);
        Livraison updated = livraisonRepository.save(livraison);

        return livraisonMapper.toDTO(updated);
    }

    public LivraisonDTO modifierStatut(Long livraisonId, StatutLivraison nouveauStatut) {
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException(
                        "Livraison non trouvée avec l'id : " + livraisonId));

        StatutLivraison ancienStatut = livraison.getStatut();
        livraison.setStatut(nouveauStatut);


        if (nouveauStatut == StatutLivraison.LIVRE) {
            if (livraison.getChauffeur() != null) {
                livraison.getChauffeur().setDisponible(true);
                chauffeurRepository.save(livraison.getChauffeur());
            }
            if (livraison.getVehicule() != null) {
                livraison.getVehicule().setStatut(StatutVehicule.DISPONIBLE);
                vehiculeRepository.save(livraison.getVehicule());
            }
        }

        Livraison updated = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(updated);
    }

    @Transactional(readOnly = true)
    public List<LivraisonDTO> listerTout() {
        return livraisonMapper.toDTOList(livraisonRepository.findAll());
    }

    @Transactional(readOnly = true)
    public LivraisonDTO getLivraisonById(Long id) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Livraison non trouvée avec l'id : " + id));
        return livraisonMapper.toDTO(livraison);
    }

    @Transactional(readOnly = true)
    public List<LivraisonDTO> listerParStatut(StatutLivraison statut) {
        return livraisonMapper.toDTOList(livraisonRepository.findByStatut(statut));
    }

    @Transactional(readOnly = true)
    public List<LivraisonDTO> listerParClient(Long clientId) {
        return livraisonMapper.toDTOList(livraisonRepository.findByClientId(clientId));
    }

    @Transactional(readOnly = true)
    public List<LivraisonDTO> listerEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin) {
        return livraisonMapper.toDTOList(
                livraisonRepository.findLivraisonsEntreDeuxDates(dateDebut, dateFin));
    }

    @Transactional(readOnly = true)
    public List<LivraisonDTO> listerParVilleDestination(String ville) {
        return livraisonMapper.toDTOList(
                livraisonRepository.findLivraisonsParVilleDestination(ville));
    }
}
