package org.example.ecommerce.service.authentication;

import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.database.models.User;

public interface IAuthService {
    RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequestDTO);
    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO);
    MyProfileResponseDTO getMyProfile(User user);
}
