package org.fleetflow.fleetflow.service;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LivraisonServiceTest {
@Mock
private ClientRepository clientRepository;
@Mock
private ChauffeurRepository chauffeurRepository;
@Mock
private VehiculeRepository vehiculeRepository;
@Mock
private LivraisonRepository livraisonRepository;
@Mock
private LivraisonMapper livraisonMapper;
@InjectMocks
private LivraisonService livraisonService;

    @Test
    void creerLivraison() {
        Client client=new Client();
        client.setClientId(1L);
        client.setNom("Nihad");

        LivraisonDTO dto=new LivraisonDTO();
        dto.setClientId(1L);
        dto.setDateLivraison(LocalDate.now());
        dto.setAdresseDepart("Beni mellal");
        dto.setAdresseDestination("Rabat");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(livraisonRepository.save(any(Livraison.class))).thenAnswer(invocation -> {
            return invocation.getArgument(0);
        });
        when(livraisonMapper.toDTO(any(Livraison.class))).thenAnswer(invocation -> {
            Livraison liv = invocation.getArgument(0);
            LivraisonDTO resultat = new LivraisonDTO();
            resultat.setStatut(liv.getStatut());
            return resultat;
        });

        LivraisonDTO resultat=livraisonService.creerLivraison(dto);
        assertEquals(StatutLivraison.EN_ATTENTE,resultat.getStatut());
    }

    @Test
    void assignerChauffeurEtVehicule() {
        Livraison livraison = new Livraison();
        livraison.setLivraisonId(1L);
        livraison.setStatut(StatutLivraison.EN_ATTENTE);


        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setChauffeurId(1L);
        chauffeur.setNom("Ali");
        chauffeur.setDisponible(true);


        Vehicule vehicule = new Vehicule();
        vehicule.setVehiculeId(1L);
        vehicule.setMatricule("ABC-123");
        vehicule.setStatut(StatutVehicule.DISPONIBLE);


        LivraisonAssignDTO assignDTO = new LivraisonAssignDTO();
        assignDTO.setChauffeurId(1L);
        assignDTO.setVehiculeId(1L);

        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(chauffeurRepository.findById(1L)).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.of(vehicule));
        when(chauffeurRepository.save(any())).thenReturn(chauffeur);
        when(vehiculeRepository.save(any())).thenReturn(vehicule);
        when(livraisonRepository.save(any())).thenReturn(livraison);

        LivraisonDTO resultatAttendu = new LivraisonDTO();
        resultatAttendu.setLivraisonId(1L);
        resultatAttendu.setStatut(StatutLivraison.EN_COURS);
        resultatAttendu.setChauffeurId(1L);
        resultatAttendu.setVehiculeId(1L);
        when(livraisonMapper.toDTO(any())).thenReturn(resultatAttendu);


        LivraisonDTO resultat = livraisonService.assignerChauffeurEtVehicule(1L, assignDTO);


        assertEquals(StatutLivraison.EN_COURS, resultat.getStatut());

        assertEquals(1L, resultat.getChauffeurId());


        assertEquals(1L, resultat.getVehiculeId());


        assertFalse(chauffeur.getDisponible());

        assertEquals(StatutVehicule.EN_LIVRAISON, vehicule.getStatut());

        verify(chauffeurRepository, times(1)).save(chauffeur);
        verify(vehiculeRepository, times(1)).save(vehicule);
        verify(livraisonRepository, times(1)).save(livraison);
    }

    @Test
    void modifierStatut() {
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setChauffeurId(1L);
        chauffeur.setNom("Ali");
        chauffeur.setDisponible(false);

        Vehicule vehicule = new Vehicule();
        vehicule.setVehiculeId(1L);
        vehicule.setMatricule("ABC-123");
        vehicule.setStatut(StatutVehicule.EN_LIVRAISON);

        Livraison livraison = new Livraison();
        livraison.setLivraisonId(1L);
        livraison.setStatut(StatutLivraison.EN_COURS);
        livraison.setChauffeur(chauffeur);
        livraison.setVehicule(vehicule);

        when(livraisonRepository.findById(1L)).thenReturn(Optional.of(livraison));
        when(chauffeurRepository.save(any())).thenReturn(chauffeur);
        when(vehiculeRepository.save(any())).thenReturn(vehicule);
        when(livraisonRepository.save(any())).thenReturn(livraison);

        LivraisonDTO resultatAttendu = new LivraisonDTO();
        resultatAttendu.setLivraisonId(1L);
        resultatAttendu.setStatut(StatutLivraison.LIVRE);
        when(livraisonMapper.toDTO(any())).thenReturn(resultatAttendu);

        LivraisonDTO resultat = livraisonService.modifierStatut(1L, StatutLivraison.LIVRE);


        assertEquals(StatutLivraison.LIVRE, resultat.getStatut());

        assertTrue(chauffeur.getDisponible());


        assertEquals(StatutVehicule.DISPONIBLE, vehicule.getStatut());

        verify(chauffeurRepository, times(1)).save(chauffeur);
        verify(vehiculeRepository, times(1)).save(vehicule);
        verify(livraisonRepository, times(1)).save(livraison);
    }
}