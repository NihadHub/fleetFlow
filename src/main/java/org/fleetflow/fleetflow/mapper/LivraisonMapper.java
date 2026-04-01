package org.fleetflow.fleetflow.mapper;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.Livraison;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivraisonMapper {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.nom", target = "clientNom")
    @Mapping(source = "chauffeur.id", target = "chauffeurId")
    @Mapping(source = "chauffeur.nom", target = "chauffeurNom")
    @Mapping(source = "vehicule.id", target = "vehiculeId")
    @Mapping(source = "vehicule.matricule", target = "vehiculeMatricule")
    LivraisonDTO toDTO(Livraison livraison);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(target = "chauffeur", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    Livraison toEntity(LivraisonDTO dto);

    List<LivraisonDTO> toDTOList(List<Livraison> livraisons);
}
