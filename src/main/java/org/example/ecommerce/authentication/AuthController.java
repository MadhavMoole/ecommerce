package org.example.ecommerce.authentication;

//region imports
import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.Login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.Login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationResponseDTO;
import org.example.ecommerce.authentication.service.AuthService;
import org.example.ecommerce.authentication.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//endregion

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthServiceResponse<RegistrationResponseDTO>> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        try {
            AuthServiceResponse<RegistrationResponseDTO> authServiceResponse = authService.registerUser(registrationRequestDTO);
            return new ResponseEntity<>(authServiceResponse, authServiceResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthServiceResponse<LoginResponseDTO>> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            AuthServiceResponse<LoginResponseDTO> authServiceResponse = authService.loginUser(loginRequestDTO);
            return new ResponseEntity<>(authServiceResponse, authServiceResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
