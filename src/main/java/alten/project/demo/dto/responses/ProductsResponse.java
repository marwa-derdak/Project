package alten.project.demo.dto.responses;

import lombok.Data;

import java.util.Date;

@Data
public class ProductsResponse {

    private String id;
    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private double price;

    private int quantity;
    private String internalReference;
    private int shellId;
    private String inventoryStatus;
    private double rating;
    private Date createdAt;
    private Date updatedAt;
}
