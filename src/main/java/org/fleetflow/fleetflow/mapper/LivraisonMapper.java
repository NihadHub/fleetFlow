package org.fleetflow.fleetflow.mapper;
import org.fleetflow.fleetflow.dto.LivraisonDTO;
import org.fleetflow.fleetflow.entity.Livraison;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  LivraisonMapper {
    @Mapping(source = "livraisonId", target = "livraisonId")
    @Mapping(source = "client.clientId", target = "clientId")
    @Mapping(source = "client.nom", target = "clientNom")
    @Mapping(source = "chauffeur.chauffeurId", target = "chauffeurId")
    @Mapping(source = "chauffeur.nom", target = "chauffeurNom")
    @Mapping(source = "vehicule.vehiculeId", target = "vehiculeId")
    @Mapping(source = "vehicule.matricule", target = "vehiculeMatricule")
    LivraisonDTO toDTO(Livraison livraison);

    @Mapping(source = "clientId", target = "client.clientId")
    Livraison toEntity(LivraisonDTO dto);

    List<LivraisonDTO> toDTOList(List<Livraison> livraisons);
}

