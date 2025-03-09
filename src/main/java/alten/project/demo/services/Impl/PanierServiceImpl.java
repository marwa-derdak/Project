package alten.project.demo.services.Impl;

import alten.project.demo.dto.requests.CreatePanierRequest;
import alten.project.demo.dto.responses.PanierResponse;
import alten.project.demo.mapper.PanierMapper;
import alten.project.demo.model.Panier;
import alten.project.demo.model.Product;
import alten.project.demo.repository.PanierRepository;
import alten.project.demo.repository.ProductRepository;
import alten.project.demo.services.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;

    private final PanierMapper panierMapper;

    private final ProductRepository productRepository;
    @Override
    public PanierResponse addProductToPanier(CreatePanierRequest request) {
        Optional<Panier> panierOptional = panierRepository.findByUserId(request.getUserId());

        if(panierOptional.isPresent()){

            Panier existingpanier = panierOptional.get();

           for (Product productRequest : request.getProducts()) {
               Optional<Product> productOptional = productRepository.findById(productRequest.getId());
               if(productOptional.isPresent()) {
                   existingpanier.ajoutProduit(productOptional.get());
               }
               else {
                   throw new NoSuchElementException("product not found : " + productRequest.getId());
               }

           }

            return panierMapper.panierToPanierResponse(panierRepository.save(existingpanier));


        } else {

            Panier newone = new Panier();
            newone.setUserId(request.getUserId());
            for(Product product : request.getProducts()) {
                newone.ajoutProduit(product);
            }
            return panierMapper.panierToPanierResponse(panierRepository.save(newone));

        }

    }

    @Override
    public Panier removeProductFromPanier(String userId, String productId) {

        Optional<Panier> panierOptional = panierRepository.findByUserId(userId);

        if (panierOptional.isPresent()) {

            Panier responepanier = panierOptional.get();

            Optional<Product> productOptional = productRepository.findById(productId);

            if(productOptional.isPresent()){
                    responepanier.removeProduct(productOptional.get());


            } else {
                throw new NoSuchElementException("Product not found : " + productId);
            }



            return panierRepository.save(responepanier);

        } else {
            throw new NoSuchElementException("Panier not found : " + userId);
        }

    }

    @Override
    public PanierResponse getPanier(String userId) {
        return panierMapper.panierToPanierResponse(panierRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Panier not found")));

    }
}
