package org.example.ecommerce.service.authentication;

import org.example.ecommerce.AuthServiceResponse;
import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.database.models.User;

public interface IAuthService {
    AuthServiceResponse<RegistrationResponseDTO> registerUser(RegistrationRequestDTO registrationRequestDTO);
    AuthServiceResponse<LoginResponseDTO> loginUser(LoginRequestDTO loginRequestDTO);
    AuthServiceResponse<MyProfileResponseDTO> getMyProfile(User user);
}
