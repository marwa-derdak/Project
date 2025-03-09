package alten.project.demo.repository;

import alten.project.demo.model.EnvieList;
import alten.project.demo.model.Panier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EnvieListRepository extends MongoRepository<EnvieList, String> {

    Optional<EnvieList> findByUserId(String userId);
}
