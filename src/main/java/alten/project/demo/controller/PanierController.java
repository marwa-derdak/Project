package alten.project.demo.controller;

import alten.project.demo.dto.requests.CreatePanierRequest;
import alten.project.demo.dto.responses.PanierResponse;
import alten.project.demo.model.Panier;
import alten.project.demo.services.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService panierService;

    @PostMapping("/ajout")
    public ResponseEntity<PanierResponse> addProductToPanier(@RequestBody CreatePanierRequest request) {

        return ResponseEntity.ok(panierService.addProductToPanier(request));
    }
    @PostMapping("/remove")
    public Panier removeProductFromPanier(@RequestParam String userId, @RequestParam String productId) {
        return panierService.removeProductFromPanier(userId, productId);
    }

    // Obtenir le panier d'un utilisateur
    @GetMapping("/{userId}")
    public ResponseEntity<PanierResponse> getPanier(@PathVariable String userId) {

        return ResponseEntity.ok(panierService.getPanier(userId));
    }
}
