package alten.project.demo.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "products")
@Getter
@Setter
@Builder
public class Product {

    @Id
    private String id;

    private String code;

    private String name;

    private String description;

    private String image;

    private String category;

    private double  price;

    private int quantity;
    private String internalReference;

    private double rating;
    private int shellId;
    private String inventoryStatus;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
