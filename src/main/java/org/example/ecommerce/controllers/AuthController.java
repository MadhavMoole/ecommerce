package org.example.ecommerce.controllers;

import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.service.authentication.AuthService;
import org.example.ecommerce.service.authentication.IAuthService;
import org.example.ecommerce.database.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RegistrationResponseDTO> registerUser(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        try {
            RegistrationResponseDTO authServiceResponse = authService.registerUser(registrationRequestDTO);
            return new ResponseEntity<>(authServiceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region login controller
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            LoginResponseDTO authServiceResponse = authService.loginUser(loginRequestDTO);
            return new ResponseEntity<>(authServiceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion

    //region my-profile controller
    @GetMapping("/my-profile")
    public ResponseEntity<MyProfileResponseDTO> getMyProfile(@AuthenticationPrincipal User user) {
        MyProfileResponseDTO myProfileResponse = authService.getMyProfile(user);
        if(myProfileResponse != null) {
            return new ResponseEntity<>(myProfileResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(myProfileResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion

}
