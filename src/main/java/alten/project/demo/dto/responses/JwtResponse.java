package alten.project.demo.dto.responses;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
}
