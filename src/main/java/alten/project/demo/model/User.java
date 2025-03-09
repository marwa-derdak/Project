package alten.project.demo.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@Builder
public class User {
    @Id
    private String id;
    private String username;
    private String firstname;
    private String email;
    private String password;
}
