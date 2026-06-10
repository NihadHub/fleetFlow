package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.fleetflow.fleetflow.service.impl.VehiculeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {

    @Mock
    private VehiculeRepository repo;
    @Mock
    private VehiculeMapper mapper;
    @InjectMocks
    private VehiculeServiceImpl service;

    @Test
    void getVehiculeByStatut() {
        StatutVehicule statut = StatutVehicule.DISPONIBLE;
        Pageable pageable = PageRequest.of(0, 10);
        
        Vehicule v1 = new Vehicule();
        v1.setVehiculeId(1L);
        v1.setStatut(statut);
        
        VehiculeResponseDTO dto1 = new VehiculeResponseDTO();
        dto1.setVehiculeId(1L);
        dto1.setStatut(statut);

        Page<Vehicule> page = new PageImpl<>(List.of(v1), pageable, 1);

        when(repo.findVehiculeByStatut(statut, pageable)).thenReturn(page);
        when(mapper.toDTO(v1)).thenReturn(dto1);

        Page<VehiculeResponseDTO> result = service.getVehiculeByStatut(statut, pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertEquals(statut, result.getContent().get(0).getStatut());
        assertEquals(1L, result.getContent().get(0).getVehiculeId());
    }

    @Test
    void getVehiculeByCapaciteGreaterThan() {
        double minCapacite = 10.0;
        Pageable pageable = PageRequest.of(0, 10);

        Vehicule v1 = new Vehicule();
        v1.setVehiculeId(1L);
        v1.setCapacite(15.0);

        VehiculeResponseDTO dto1 = new VehiculeResponseDTO();
        dto1.setVehiculeId(1L);
        dto1.setCapacite(15.0);

        Page<Vehicule> page = new PageImpl<>(List.of(v1), pageable, 1);

        when(repo.findVehiculeByCapaciteGreaterThan(minCapacite, pageable)).thenReturn(page);
        when(mapper.toDTO(v1)).thenReturn(dto1);

        Page<VehiculeResponseDTO> result = service.getVehiculeByCapaciteGreaterThan(minCapacite, pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().get(0).getCapacite() > minCapacite);
        assertEquals(15.0, result.getContent().get(0).getCapacite());
    }
}
