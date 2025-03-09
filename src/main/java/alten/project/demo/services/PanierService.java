package alten.project.demo.services;

import alten.project.demo.dto.requests.CreatePanierRequest;
import alten.project.demo.dto.responses.PanierResponse;
import alten.project.demo.model.Panier;
import org.springframework.web.bind.annotation.RequestParam;

public interface PanierService {


    PanierResponse addProductToPanier(CreatePanierRequest request);

    Panier removeProductFromPanier(String userId, String productId);

    PanierResponse getPanier(String userId);


}
