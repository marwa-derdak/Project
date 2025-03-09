package alten.project.demo.services.Impl;

import alten.project.demo.dto.requests.CreateProductsRequest;
import alten.project.demo.dto.responses.ProductsResponse;
import alten.project.demo.mapper.ProductMapper;
import alten.project.demo.model.Product;
import alten.project.demo.repository.ProductRepository;
import alten.project.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductsResponse createProduct(CreateProductsRequest request) {
       validerexistingproductbycode(request.getCode());

        Product product = Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .internalReference(request.getInternalReference())
                .shellId(request.getShellId())
                .inventoryStatus(request.getInventoryStatus().toString())
                .rating(request.getRating())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();

        return productMapper.productsToProductResponse(productRepository.save(product));
    }

    private void validerexistingproductbycode(String code) {
        Optional<Product> productOptional = productRepository.findByCode(code);
        if(productOptional.isPresent()){
            throw new RuntimeException("Product already exist with "+code);
        }
    }

    @Override
    public List<ProductsResponse> getallProduct() {
        return productRepository.findAll().stream()
                .map(productMapper::productsToProductResponse) // Method reference
                .collect(Collectors.toList());
    }

    @Override
    public ProductsResponse getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {

            Product responeproduct = productOptional.get();

            return productMapper.productsToProductResponse(responeproduct);

        } else {
            throw new NoSuchElementException("Product not found : " + id);
        }
    }

    @Override
    public ProductsResponse updateProduct(String id, CreateProductsRequest productUpdates) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product existingProduct = productOptional.get();
        if (productOptional.isPresent()) {
            if (productUpdates.getCode() != null) {
                existingProduct.setCode(productUpdates.getCode());
            }
            if (productUpdates.getName() != null) {
                existingProduct.setName(productUpdates.getName());
            }
            if (productUpdates.getDescription() != null) {
                existingProduct.setDescription(productUpdates.getDescription());
            }
            if (productUpdates.getImage() != null) {
                existingProduct.setImage(productUpdates.getImage());
            }
            if (productUpdates.getCategory() != null) {
                existingProduct.setCategory(productUpdates.getCategory());
            }
            if (productUpdates.getPrice()  != 0.0) {
                existingProduct.setPrice(productUpdates.getPrice());
            }
            if (productUpdates.getQuantity()  != 0) {
                existingProduct.setQuantity(productUpdates.getQuantity());
            }
            if (productUpdates.getInternalReference() != null) {
                existingProduct.setInternalReference(productUpdates.getInternalReference());
            }
            if (productUpdates.getShellId()  != 0) {
                existingProduct.setShellId(productUpdates.getShellId());
            }
            if (productUpdates.getInventoryStatus() != null) {
                existingProduct.setInventoryStatus(productUpdates.getInventoryStatus().toString());
            }
            if (productUpdates.getRating()  != 0.0) {
                existingProduct.setRating(productUpdates.getRating());
            }
            if (productUpdates.getCreatedAt() != null) {
                existingProduct.setCreatedAt(productUpdates.getCreatedAt());
            }
            if (productUpdates.getUpdatedAt() != null) {
                existingProduct.setUpdatedAt(productUpdates.getUpdatedAt());
            }

            return productMapper.productsToProductResponse(productRepository.save(existingProduct));

        } else {
            throw new NoSuchElementException("Product not found : " + id);
        }


    }

    @Override
    public void deleteProduct(String id) {

        Optional<Product> productOptional = productRepository.findById(id);
        Product existingProduct = productOptional.get();
        if (productOptional.isPresent()) {
            // Delete the product
            productRepository.delete(existingProduct);

        }else {
            throw new NoSuchElementException("Product not found : " + id);
        }


    }
}
