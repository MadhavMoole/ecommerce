package org.example.ecommerce.authentication.service;

import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.registration.RegistrationResponseDTO;
import org.example.ecommerce.authentication.model.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.database.models.User;

public interface IAuthService {
    AuthServiceResponse<RegistrationResponseDTO> registerUser(RegistrationRequestDTO registrationRequestDTO);
    AuthServiceResponse<LoginResponseDTO> loginUser(LoginRequestDTO loginRequestDTO);
    AuthServiceResponse<MyProfileResponseDTO> getMyProfile(User user);
}
