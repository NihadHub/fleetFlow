package org.fleetflow.fleetflow.service.impl;

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
import org.fleetflow.fleetflow.service.interfaces.LivraisonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LivraisonServiceImpl implements LivraisonService {
    private final LivraisonRepository livraisonRepository;
    private final ClientRepository clientRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LivraisonMapper livraisonMapper;

    @Override
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

        if (dto.getChauffeurId() != null) {
            Chauffeur chauffeur = chauffeurRepository.findById(dto.getChauffeurId())
                    .orElseThrow(() -> new RuntimeException("Chauffeur non trouvé"));
            if (chauffeur.getDisponible()) {
                livraison.setChauffeur(chauffeur);
                chauffeur.setDisponible(false);
                chauffeurRepository.save(chauffeur);
            }
        }

        if (dto.getVehiculeId() != null) {
            Vehicule vehicule = vehiculeRepository.findById(dto.getVehiculeId())
                    .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
            if (vehicule.getStatut() == StatutVehicule.DISPONIBLE) {
                livraison.setVehicule(vehicule);
                vehicule.setStatut(StatutVehicule.EN_LIVRAISON);
                vehiculeRepository.save(vehicule);
            }
        }

        if (livraison.getChauffeur() != null && livraison.getVehicule() != null) {
            livraison.setStatut(StatutLivraison.EN_COURS);
        }

        Livraison saved = livraisonRepository.save(livraison);
        return livraisonMapper.toDTO(saved);
    }

    @Override
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


    @Override
    public LivraisonDTO modifierStatut(Long livraisonId, StatutLivraison nouveauStatut) {

            Livraison livraison = livraisonRepository.findById(livraisonId)
                    .orElseThrow(() -> new RuntimeException(
                            "Livraison non trouvée avec l'id : " + livraisonId));

            StatutLivraison ancienStatut = livraison.getStatut();
            livraison.setStatut(nouveauStatut);


            if (nouveauStatut == StatutLivraison.LIVRE || nouveauStatut == StatutLivraison.ANNULEE) {
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

    @Override
    public List<LivraisonDTO> listerTout() {
        return livraisonMapper.toDTOList(livraisonRepository.findAll());
    }

    @Override
    public LivraisonDTO getLivraisonById(Long id) {
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Livraison non trouvée avec l'id : " + id));
        return livraisonMapper.toDTO(livraison);
    }

    @Override
    public List<LivraisonDTO> listerParStatut(StatutLivraison statut) {
        return livraisonMapper.toDTOList(livraisonRepository.findByStatut(statut));
    }

    @Override
    public List<LivraisonDTO> listerParClient(Long clientId) {
        return livraisonMapper.toDTOList(livraisonRepository.findByClientClientId(clientId));
    }

    @Override
    public List<LivraisonDTO> listerEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin) {
        return livraisonMapper.toDTOList(
                livraisonRepository.findLivraisonsEntreDeuxDates(dateDebut, dateFin));
    }

    @Override
    public List<LivraisonDTO> listerParVilleDestination(String ville) {
        return livraisonMapper.toDTOList(
                livraisonRepository.findLivraisonsParVilleDestination(ville));
    }
}
