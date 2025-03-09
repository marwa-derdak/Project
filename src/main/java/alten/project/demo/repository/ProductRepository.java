package alten.project.demo.repository;

import alten.project.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'code' : ?0 }")
    Optional<Product> findByCode(String code);
}
