package org.fleetflow.fleetflow.mapper;

import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeRequestDTO;
import org.fleetflow.fleetflow.dto.vehiculeDTO.VehiculeResponseDTO;
import org.mapstruct.Mapper;
import org.fleetflow.fleetflow.entity.Vehicule;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    @Mapping(target = "vehiculeId", ignore = true)
    @Mapping(target = "livraison", ignore = true)
    Vehicule toEntity(VehiculeRequestDTO dto);

    VehiculeResponseDTO toDTO(Vehicule vehicule);

    @Mapping(target = "vehiculeId", ignore = true)
    @Mapping(target = "livraison", ignore = true)
    void updateVehiculeFromDto(VehiculeRequestDTO dto, @MappingTarget Vehicule entity);
}
