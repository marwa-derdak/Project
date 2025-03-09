package alten.project.demo.mapper;

import alten.project.demo.dto.responses.ProductsResponse;
import alten.project.demo.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductsResponse productsToProductResponse(Product product);
}
