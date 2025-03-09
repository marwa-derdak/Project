package alten.project.demo.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Document(collection = "panier")
public class Panier {

    @Id
    private String id;

    private String userId;

    private List<Product> products = new ArrayList<>();

    private double prixtotal;

    public void ajoutProduit(Product product) {

        products.add(product);
        prixtotal += product.getPrice();
    }


    public void removeProduct(Product product) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(product.getId())) {
                iterator.remove(); // Safely remove item while iterating
                prixtotal -= product.getPrice();
                break; // Exit loop after finding and removing the product
            }
        }

    }


}
