package alten.project.demo.controller;


import alten.project.demo.dto.requests.CreateProductsRequest;
import alten.project.demo.dto.responses.ProductsResponse;
import alten.project.demo.model.Product;
import alten.project.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody CreateProductsRequest productsRequest) {

        return ResponseEntity.ok(productService.createProduct(productsRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductsResponse>> getAllproducts() {

        List<ProductsResponse> responses = productService.getallProduct();
        return ResponseEntity.ok(responses);


    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductsResponse> UpdateProduct(@PathVariable String id, @RequestBody CreateProductsRequest productUpdates) {

        return ResponseEntity.ok(productService.updateProduct(id, productUpdates));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponse> getProductbyId(@PathVariable String id) {

        return ResponseEntity.ok(productService.getProductById(id));


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}


