package org.example.ecommerce.service.authentication;

import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.exception.EmailFailureException;
import org.example.ecommerce.exception.InvalidCredentialException;
import org.example.ecommerce.exception.UserAlreadyExistsException;
import org.example.ecommerce.exception.UserNotFoundException;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.database.models.User;

public interface IAuthService {
    RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequestDTO) throws EmailFailureException, UserAlreadyExistsException;
    LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) throws UserNotFoundException, InvalidCredentialException;
    MyProfileResponseDTO getMyProfile(User user);
}
