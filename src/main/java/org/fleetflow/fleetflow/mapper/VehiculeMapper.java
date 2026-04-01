package org.fleetflow.fleetflow.mapper;

import org.fleetflow.fleetflow.dto.ClientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.VehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.VehiculeDTO.VehiculeResponseDTO;
import org.fleetflow.fleetflow.entity.Client;
import org.mapstruct.Mapper;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    Vehicule toEntity(VehiculeRequestDTO dto);

    VehiculeResponseDTO toDTO(Vehicule vehicule);

    @Mapping(target = "vehiculeId", ignore = true)
    void updateVehiculeFromDto(VehiculeRequestDTO dto, @MappingTarget Vehicule entity);
}
