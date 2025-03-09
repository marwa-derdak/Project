package alten.project.demo.repository;

import alten.project.demo.model.Panier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PanierRepository extends MongoRepository<Panier, String> {
    Optional<Panier> findByUserId(String userId);
}
