package org.example.ecommerce.authentication.service;

//region imports
import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.Login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.Login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationResponseDTO;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.utils.EncryptionService;
import org.example.ecommerce.utils.JWTService;
import org.example.ecommerce.utils.ValidatationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
//endregion

@Service
public class AuthService implements IAuthService{

    //region declaration
    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    //endregion

    @Autowired
    public void setJwtService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {this.encryptionService = encryptionService;}

    //region register
    @Override
    public AuthServiceResponse<RegistrationResponseDTO> registerUser(RegistrationRequestDTO registrationRequestDTO) {

        if(!ValidatationService.isValidEmail(registrationRequestDTO.email())) {
            logger.error("AuthService => createUser => Error: Invalid Email");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Invalid Email", null);
        }

        if(!ValidatationService.isValidPassword(registrationRequestDTO.password())) {
            logger.error("AuthService => createUser => Error: Invalid Password");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Invalid Password", null);
        }

        if(userRepository.findByUsername(registrationRequestDTO.username()).isPresent()) {
            logger.error("AuthService => createUser => Error: User Already exists");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "User Already Exists", null);
        }

        if(userRepository.findByEmail(registrationRequestDTO.email()).isPresent()) {
            logger.error("AuthService => createUser => Error: Email Already taken");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Email Already taken", null);
        }

        try {
            User user = new User();
            user.setUsername(registrationRequestDTO.username());
            user.setFirstName(registrationRequestDTO.firstname());
            user.setLastName(registrationRequestDTO.lastname());
            user.setEmail(registrationRequestDTO.email());
            user.setPassword(encryptionService.encrypt(registrationRequestDTO.password()));
            userRepository.save(user);

            // Generate JWT token on registration
            String jwt = jwtService.generateJWT(user);
            RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO(jwt, user.getUsername(), user.getEmail());

            logger.info("AuthService => createUser => User created");
            return new AuthServiceResponse<>(HttpStatus.OK, "User created", registrationResponseDTO);
        } catch (DataAccessException e) {
            logger.error("AuthService => createUser => Error: {}", e.getMessage());
            return new AuthServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    //endregion

    //region login
    @Override
    public AuthServiceResponse<LoginResponseDTO> loginUser(LoginRequestDTO loginRequestDTO) {
        Optional<User> opUser = userRepository.findByUsername(loginRequestDTO.username());
        if (opUser.isEmpty()) {
            logger.error("AuthService => login => Error: User not found");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "User not found", null);
        }

        User user = opUser.get();
        if (!encryptionService.verifyPassword(loginRequestDTO.password(), user.getPassword())) {
            logger.error("AuthService => login => Error: Incorrect password");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Incorrect password", null);
        }

        String jwt = jwtService.generateJWT(user);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwt, user.getUsername(), user.getEmail());
        logger.info("AuthService => login => User logged in successfully");
        return new AuthServiceResponse<>(HttpStatus.OK, "User logged in", loginResponseDTO);
    }
    //endregion

}
