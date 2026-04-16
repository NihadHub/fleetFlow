package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.enums.TypePermis;
import org.fleetflow.fleetflow.mapper.ChauffeurMapper;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChauffeurServiceTest {
    @Mock
    private ChauffeurRepository chauffeurRepository;
    @Mock
    private ChauffeurMapper chauffeurMapper;
    @InjectMocks
    private ChauffeurService chauffeurService;
    @Test
    void listerDisponibles() {
        Chauffeur chauffeur = new Chauffeur(1L, "Nihad", "0674329", TypePermis.C, true);
        ChauffeurDTO dto = new ChauffeurDTO(1L, "Nihad", "0674329", TypePermis.C, true);

        List<Chauffeur> chauffeurs = List.of(chauffeur);
        List<ChauffeurDTO> dtos = List.of(dto);

        when(chauffeurRepository.findByDisponibleTrue()).thenReturn(chauffeurs);

        when(chauffeurMapper.toDTOList(chauffeurs)).thenReturn(dtos);

        List<ChauffeurDTO> result = chauffeurService.listerDisponibles();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Nihad", result.get(0).getNom());

        verify(chauffeurRepository).findByDisponibleTrue();
        verify(chauffeurMapper).toDTOList(anyList());
    }

}



