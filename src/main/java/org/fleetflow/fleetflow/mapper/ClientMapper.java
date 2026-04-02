package org.fleetflow.fleetflow.mapper;

import org.fleetflow.fleetflow.dto.clientDTO.ClientRequestDTO;
import org.fleetflow.fleetflow.dto.clientDTO.ClientResponseDTO;
import org.mapstruct.Mapper;
import org.fleetflow.fleetflow.entity.Client;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientRequestDTO dto);

    ClientResponseDTO toResponseDTO(Client client);

    @Mapping(target = "id", ignore = true)
    void updateClientFromDto(ClientRequestDTO dto, @MappingTarget Client entity);
}