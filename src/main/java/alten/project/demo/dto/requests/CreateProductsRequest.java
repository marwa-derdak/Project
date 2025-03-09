package alten.project.demo.dto.requests;

import alten.project.demo.dto.Enums.InventoryStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateProductsRequest {
    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private double  price;

    private int quantity;
    private String internalReference;
    private int shellId;
    private InventoryStatusEnum inventoryStatus;
    private double rating;
    private Date createdAt;
    private Date updatedAt;
}
