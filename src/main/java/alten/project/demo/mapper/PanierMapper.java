package alten.project.demo.mapper;

import alten.project.demo.dto.responses.PanierResponse;
import alten.project.demo.model.Panier;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PanierMapper {

    PanierResponse panierToPanierResponse(Panier panier);
}
