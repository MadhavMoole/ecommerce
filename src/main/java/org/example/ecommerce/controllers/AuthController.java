package org.example.ecommerce.controllers;

import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.exception.EmailFailureException;
import org.example.ecommerce.exception.InvalidCredentialException;
import org.example.ecommerce.exception.TokenNotFoundException;
import org.example.ecommerce.exception.UserAlreadyExistsException;
import org.example.ecommerce.exception.UserNotFoundException;
import org.example.ecommerce.exception.UserNotVerifiedException;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.service.authentication.AuthService;
import org.example.ecommerce.service.authentication.IAuthService;
import org.example.ecommerce.database.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth/v1")
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
    public ResponseEntity<RegistrationResponseDTO> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) throws EmailFailureException, UserAlreadyExistsException {
        RegistrationResponseDTO authServiceResponse = authService.registerUser(registrationRequestDTO);
        return new ResponseEntity<>(authServiceResponse, HttpStatus.OK);
    }
    //endregion

    //region login controller
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws UserNotFoundException, InvalidCredentialException, EmailFailureException, UserNotVerifiedException {
        LoginResponseDTO authServiceResponse = authService.loginUser(loginRequestDTO);
        return new ResponseEntity<>(authServiceResponse, HttpStatus.OK);
    }
    //endregion

    //region my-profile controller
    @GetMapping("/my-profile")
    public ResponseEntity<MyProfileResponseDTO> getMyProfile(@AuthenticationPrincipal User user) throws UserNotFoundException {
        MyProfileResponseDTO myProfileResponse = authService.getMyProfile(user);
        return new ResponseEntity<>(myProfileResponse, HttpStatus.OK);
    }
    //endregion

    //region email-verification controller
    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody String token) throws TokenNotFoundException {
        if(authService.verifyUser(token)) {
            return new ResponseEntity<String>("USER VERIFIED", HttpStatus.OK);
        }
        return new ResponseEntity<String>("ERROR OCCURED", HttpStatus.CONFLICT);
    }
    //endregion
}
