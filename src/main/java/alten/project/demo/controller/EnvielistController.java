package alten.project.demo.controller;


import alten.project.demo.dto.requests.CreateEnvielistRequest;
import alten.project.demo.dto.responses.EnvieListResponse;
import alten.project.demo.services.EnvielistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/envielist")
public class EnvielistController {

    private final EnvielistService envielistService;

    @PostMapping("/ajout")
    public ResponseEntity<EnvieListResponse> addProductToenvielist(@RequestBody CreateEnvielistRequest envielistRequest) {

        return ResponseEntity.ok(envielistService.addProductToenvielist(envielistRequest));
    }
    @PostMapping("/remove")
    public EnvieListResponse removeProductFromenvielist(@RequestParam String userId, @RequestParam String productId) {
        return envielistService.removeProductFromenvielist(userId, productId);
    }

    // Obtenir le panier d'un utilisateur
    @GetMapping("/{userId}")
    public ResponseEntity<EnvieListResponse> getenvielist(@PathVariable String userId) {

        return ResponseEntity.ok(envielistService.getenvielist(userId));
    }


}
