package alten.project.demo.services.Impl;


import alten.project.demo.dto.requests.CreateEnvielistRequest;
import alten.project.demo.dto.responses.EnvieListResponse;
import alten.project.demo.mapper.EnvieListMapper;

import alten.project.demo.model.EnvieList;
import alten.project.demo.model.Product;
import alten.project.demo.repository.EnvieListRepository;
import alten.project.demo.repository.ProductRepository;
import alten.project.demo.services.EnvielistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EnvielistServiceImpl implements EnvielistService {

    private final EnvieListRepository envieListRepository;

    private final EnvieListMapper envieListMapper;

    private final ProductRepository productRepository;
    @Override
    public EnvieListResponse addProductToenvielist(CreateEnvielistRequest envielistRequest) {

        Optional<EnvieList> envieListOptional = envieListRepository.findByUserId(envielistRequest.getUserId());

        if(envieListOptional.isPresent()){

            EnvieList envieList = envieListOptional.get();

            for (Product productRequest : envielistRequest.getProducts()) {
                Optional<Product> productOptional = productRepository.findById(productRequest.getId());
                if(productOptional.isPresent()) {
                    envieList.ajoutProduit(productOptional.get());
                }
                else {
                    throw new NoSuchElementException("product not found : " + productRequest.getId());
                }

            }

            return envieListMapper.envielistToEnvielistResponse(envieListRepository.save(envieList));


        } else {

            EnvieList newone = new EnvieList();
            newone.setUserId(envielistRequest.getUserId());
            for(Product product : envielistRequest.getProducts()) {
                newone.ajoutProduit(product);
            }
            return envieListMapper.envielistToEnvielistResponse(envieListRepository.save(newone));

        }
    }

    @Override
    public EnvieListResponse removeProductFromenvielist(String userId, String productId) {

        Optional<EnvieList> envieListOptional = envieListRepository.findByUserId(userId);

        if (envieListOptional.isPresent()) {

            EnvieList envieList = envieListOptional.get();

            Optional<Product> productOptional = productRepository.findById(productId);

            if(productOptional.isPresent()){
                envieList.removeProduct(productOptional.get());


            } else {
                throw new NoSuchElementException("Product not found : " + productId);
            }

            return envieListMapper.envielistToEnvielistResponse(envieListRepository.save(envieList));

        } else {
            throw new NoSuchElementException("Wishlist with user not found : " + userId);
        }
    }

    @Override
    public EnvieListResponse getenvielist(String userId) {
        return envieListMapper.envielistToEnvielistResponse(envieListRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("envieList with userId not found")));
    }
}
