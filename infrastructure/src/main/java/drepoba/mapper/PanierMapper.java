package drepoba.mapper;
import drepoba.domain.Panier;
import drepoba.model.PanierEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface PanierMapper {
    Panier toDTO(PanierEntity panierEntity);
    PanierEntity toEntity(Panier panier);
    List<PanierEntity> toEntityList(List<Panier> panierList);
    List<Panier> toDTOList(List<PanierEntity> PanierEntityList);
}
