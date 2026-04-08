package org.fleetflow.fleetflow.mapper;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.dto.ChauffeurDTO;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel ="spring")
public interface ChauffeurMapper {
    ChauffeurDTO toDTO(Chauffeur chauffeur);
    Chauffeur toEntity(ChauffeurDTO dto);
    List<ChauffeurDTO> toDTOList(List<Chauffeur>chauffeurs);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ChauffeurDTO dto, @MappingTarget Chauffeur chauffeur);
}

