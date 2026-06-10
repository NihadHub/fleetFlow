package org.fleetflow.fleetflow.mapper;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel ="spring")
public interface ChauffeurMapper {
    @Mapping(target = "id", source = "id")
    ChauffeurDTO toDTO(Chauffeur chauffeur);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", constant = "CHAUFFEUR")
    Chauffeur toEntity(ChauffeurDTO dto);

    List<ChauffeurDTO> toDTOList(List<Chauffeur>chauffeurs);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntityFromDTO(ChauffeurDTO dto, @MappingTarget Chauffeur chauffeur);
}

