package org.example.ecommerce.authentication.service;

import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.Login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.Login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationResponseDTO;

public interface IAuthService {
    AuthServiceResponse<RegistrationResponseDTO> registerUser(RegistrationRequestDTO registrationRequestDTO);
    AuthServiceResponse<LoginResponseDTO> loginUser(LoginRequestDTO loginRequestDTO);
}
