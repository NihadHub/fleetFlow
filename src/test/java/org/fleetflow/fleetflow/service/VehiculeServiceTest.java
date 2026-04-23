package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.fleetflow.fleetflow.enums.StatutVehicule;
import org.fleetflow.fleetflow.enums.TypeVehicule;
import org.fleetflow.fleetflow.mapper.VehiculeMapper;
import org.fleetflow.fleetflow.repository.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private VehiculeService service;

    @Test
    void getVehiculeByStatut() {
        StatutVehicule statut = StatutVehicule.DISPONIBLE;
        Vehicule v1 = new Vehicule();
        v1.setVehiculeId(1L);
        v1.setStatut(statut);
        
        VehiculeResponseDTO dto1 = new VehiculeResponseDTO();
        dto1.setVehiculeId(1L);
        dto1.setStatut(statut);

        when(repo.findVehiculeByStatut(statut)).thenReturn(List.of(v1));
        when(mapper.toDTO(v1)).thenReturn(dto1);

        List<VehiculeResponseDTO> result = service.getVehiculeByStatut(statut);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(statut, result.get(0).getStatut());
        assertEquals(1L, result.get(0).getVehiculeId());
    }

    @Test
    void getVehiculeByCapaciteGreaterThan() {
        double minCapacite = 10.0;
        Vehicule v1 = new Vehicule();
        v1.setVehiculeId(1L);
        v1.setCapacite(15.0);

        VehiculeResponseDTO dto1 = new VehiculeResponseDTO();
        dto1.setVehiculeId(1L);
        dto1.setCapacite(15.0);

        when(repo.findVehiculeByCapaciteGreaterThan(minCapacite)).thenReturn(List.of(v1));
        when(mapper.toDTO(v1)).thenReturn(dto1);

        List<VehiculeResponseDTO> result = service.getVehiculeByCapaciteGreaterThan(minCapacite);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertTrue(result.get(0).getCapacite() > minCapacite);
        assertEquals(15.0, result.get(0).getCapacite());
    }
}
