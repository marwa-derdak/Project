package alten.project.demo.dto.responses;

import lombok.Data;

import java.util.List;

@Data
public class EnvieListResponse {

    private String id;

    private String userId;
    private List<ProductsResponse> products;
}
