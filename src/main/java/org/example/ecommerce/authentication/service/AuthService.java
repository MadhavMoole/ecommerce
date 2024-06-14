package org.example.ecommerce.authentication.service;

import org.example.ecommerce.authentication.model.AuthServiceResponse;
import org.example.ecommerce.authentication.model.Login.LoginRequestDTO;
import org.example.ecommerce.authentication.model.Login.LoginResponseDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationRequestDTO;
import org.example.ecommerce.authentication.model.Registration.RegistrationResponseDTO;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.utils.EncryptionService;
import org.example.ecommerce.utils.JWTUtil;
import org.example.ecommerce.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    private UserRepository userRepository;
    private EncryptionService encryptionService;
    private JWTUtil jwtUtil;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {this.encryptionService = encryptionService;}

    @Override
    public AuthServiceResponse<RegistrationResponseDTO> registerUser(RegistrationRequestDTO registrationRequestDTO) {

        if(!Validator.isValidEmail(registrationRequestDTO.email())) {
            logger.error("AuthService => createUser => Error: Invalid Email");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Invalid Email", null);
        }

        if(!Validator.isValidPassword(registrationRequestDTO.password())) {
            logger.error("AuthService => createUser => Error: Invalid Password");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Invalid Password", null);
        }

        if(userRepository.findByUsername(registrationRequestDTO.userName()).isPresent()) {
            logger.error("AuthService => createUser => Error: User Already exists");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "User Already Exists", null);
        }

        if(userRepository.findByEmail(registrationRequestDTO.email()).isPresent()) {
            logger.error("AuthService => createUser => Error: Email Already taken");
            return new AuthServiceResponse<>(HttpStatus.BAD_REQUEST, "Email Already taken", null);
        }

        try {
            User user = new User();
            user.setUsername(registrationRequestDTO.userName());
            user.setFirstName(registrationRequestDTO.firstName());
            user.setLastName(registrationRequestDTO.lastName());
            user.setEmail(registrationRequestDTO.email());
            user.setPassword(encryptionService.encrypt(registrationRequestDTO.password()));
            userRepository.save(user);

            // Generate JWT token on registration
            String jwt = jwtUtil.generateJWT(user);
            RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO(jwt, user.getUsername(), user.getEmail());

            logger.info("AuthService => createUser => User created");
            return new AuthServiceResponse<>(HttpStatus.OK, "User created", registrationResponseDTO);
        } catch (DataAccessException e) {
            logger.error("AuthService => createUser => Error: {}", e.getMessage());
            return new AuthServiceResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

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

        String jwt = jwtUtil.generateJWT(user);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwt, user.getUsername(), user.getEmail());
        logger.info("AuthService => login => User logged in successfully");
        return new AuthServiceResponse<>(HttpStatus.OK, "User logged in", loginResponseDTO);
    }
}
