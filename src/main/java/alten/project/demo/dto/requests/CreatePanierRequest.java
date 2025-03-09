package alten.project.demo.dto.requests;

import alten.project.demo.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class CreatePanierRequest {


    private String userId;

    private List<Product> products;


}
