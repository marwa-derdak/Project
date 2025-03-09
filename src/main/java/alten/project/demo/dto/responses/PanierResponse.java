package alten.project.demo.dto.responses;

import lombok.Data;

import java.util.List;

@Data
public class PanierResponse {

    private String id;

    private String userId;

    private List<ProductsResponse> products;

    private double prixtotal;


}
