package alten.project.demo.controller;



import alten.project.demo.dto.requests.UserRequest;
import alten.project.demo.dto.responses.JwtResponse;
import alten.project.demo.model.Product;
import alten.project.demo.model.User;
import alten.project.demo.repository.UserRepository;
import alten.project.demo.security.jwt.JwtUtils;
import alten.project.demo.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserRequest userRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(setuserdetailsToJwtResponse(userDetails,jwt));
    }

    private JwtResponse setuserdetailsToJwtResponse(UserDetailsImpl userDetails,String jwt) {
        JwtResponse response =new JwtResponse();
        response.setId(userDetails.getId());
        response.setUsername( userDetails.getUsername());
        response.setEmail(userDetails.getEmail());
        response.setToken(jwt);

        return response;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = User.builder()
                .username(userRequest.getUsername())
                .firstname(userRequest.getFirstname())
                .email(userRequest.getEmail())
                .password(encoder.encode(userRequest.getPassword()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
