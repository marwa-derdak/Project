package alten.project.demo.services;

import alten.project.demo.dto.requests.CreateProductsRequest;
import alten.project.demo.dto.responses.ProductsResponse;

import java.util.List;

public interface ProductService {

    ProductsResponse createProduct(CreateProductsRequest request);

    List<ProductsResponse> getallProduct();

    ProductsResponse getProductById(String id);

    ProductsResponse updateProduct(String id,CreateProductsRequest request);

    void deleteProduct(String id);
}
