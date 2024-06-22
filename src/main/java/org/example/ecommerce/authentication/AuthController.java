package org.example.ecommerce.authentication;

//region imports
import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.Login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.Login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationResponseDTO;
import org.example.ecommerce.authentication.model.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.authentication.service.AuthService;
import org.example.ecommerce.authentication.service.IAuthService;
import org.example.ecommerce.database.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
//endregion

@RestController
@RequestMapping("/auth")
public class AuthController {

    //region declaration
    private final IAuthService authService;
    //endregion

    //region di
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    //endregion

    //region register controller
    @PostMapping("/register")
    public ResponseEntity<AuthServiceResponse<RegistrationResponseDTO>> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        try {
            AuthServiceResponse<RegistrationResponseDTO> authServiceResponse = authService.registerUser(registrationRequestDTO);
            return new ResponseEntity<>(authServiceResponse, authServiceResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region login controller
    @PostMapping("/login")
    public ResponseEntity<AuthServiceResponse<LoginResponseDTO>> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            AuthServiceResponse<LoginResponseDTO> authServiceResponse = authService.loginUser(loginRequestDTO);
            return new ResponseEntity<>(authServiceResponse, authServiceResponse.status());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region my-profile
    @GetMapping("/my-profile")
    public ResponseEntity<AuthServiceResponse<MyProfileResponseDTO>> getMyProfile(@AuthenticationPrincipal User user) {
        AuthServiceResponse<MyProfileResponseDTO> myProfileResponse = authService.getMyProfile(user);
        if(myProfileResponse.status() != HttpStatus.OK) {
            return new ResponseEntity<>(null, myProfileResponse.status());
        }
        return new ResponseEntity<>(myProfileResponse, myProfileResponse.status());
    }
    //endregion

}
