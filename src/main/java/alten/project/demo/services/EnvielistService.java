package alten.project.demo.services;

import alten.project.demo.dto.requests.CreateEnvielistRequest;
import alten.project.demo.dto.requests.CreatePanierRequest;
import alten.project.demo.dto.responses.EnvieListResponse;
import alten.project.demo.model.EnvieList;
import alten.project.demo.model.Product;


public interface EnvielistService {

    EnvieListResponse addProductToenvielist(CreateEnvielistRequest envielistRequest);

    EnvieListResponse removeProductFromenvielist(String userId, String productId);

    EnvieListResponse getenvielist(String userId);
}
