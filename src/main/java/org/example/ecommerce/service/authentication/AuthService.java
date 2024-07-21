package org.example.ecommerce.service.authentication;

import org.example.ecommerce.dto.authentication.login.LoginRequestDTO;
import org.example.ecommerce.dto.authentication.login.LoginResponseDTO;
import org.example.ecommerce.dto.AddressDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationRequestDTO;
import org.example.ecommerce.dto.authentication.registration.RegistrationResponseDTO;
import org.example.ecommerce.exception.EmailFailureException;
import org.example.ecommerce.exception.InvalidCredentialException;
import org.example.ecommerce.exception.UserAlreadyExistsException;
import org.example.ecommerce.exception.UserNotFoundException;
import org.example.ecommerce.dto.authentication.myProfile.MyProfileResponseDTO;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.models.VerificationToken;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.database.repository.VerificationTokenRepository;
import org.example.ecommerce.service.EmailService;
import org.example.ecommerce.service.EncryptionService;
import org.example.ecommerce.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService{

    //region declaration
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private EncryptionService encryptionService;

    private JWTService jwtService;
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    //endregion

    //region di
    @Autowired
    public void setJwtUtil(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {this.encryptionService = encryptionService;}

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setVerificationTokenRepository(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }
    //endregion

    //region register
    @Override
    public RegistrationResponseDTO registerUser(RegistrationRequestDTO registrationRequestDTO) throws EmailFailureException, UserAlreadyExistsException {

        if(userRepository.findByUsername(registrationRequestDTO.username()).isPresent()) {
            logger.error("AuthService => createUser => Error: User Already exists");
            throw new UserAlreadyExistsException("User Already Exists!");
        }

        if(userRepository.findByEmail(registrationRequestDTO.email()).isPresent()) {
            logger.error("AuthService => createUser => Error: Email Already taken");
            throw new UserAlreadyExistsException("User Already Exists with this email!");
        }

        try {
            User user = new User();
            user.setUsername(registrationRequestDTO.username());
            user.setfirstname(registrationRequestDTO.firstname());
            user.setLastname(registrationRequestDTO.lastname());
            user.setEmail(registrationRequestDTO.email());
            user.setPassword(encryptionService.encrypt(registrationRequestDTO.password()));

            VerificationToken verificationToken = createVerificationToken(user);
            emailService.SendVerificationMail(verificationToken);
            
            userRepository.save(user);
            verificationTokenRepository.save(verificationToken);

            // Generate JWT token on registration
            String jwt = jwtService.generateJWT(user);
            RegistrationResponseDTO registrationResponseDTO = new RegistrationResponseDTO(jwt, user.getUsername(), user.getEmail());

            logger.info("AuthService => createUser => User created");
            return registrationResponseDTO;
        } catch (DataAccessException e) {
            logger.error("AuthService => createUser => Error: {}", e.getMessage());
            return null;
        }
    }
    //endregion

    //region login
    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) throws UserNotFoundException, InvalidCredentialException {
        Optional<User> opUser = userRepository.findByUsername(loginRequestDTO.username());
        if (opUser.isEmpty()) {
            logger.error("AuthService => login => Error: User not found");
            throw new UserNotFoundException("User not found");
        }

        User user = opUser.get();
        if (!encryptionService.verifyPassword(loginRequestDTO.password(), user.getPassword())) {
            logger.error("AuthService => login => Error: Incorrect password");
            throw new InvalidCredentialException("incorrect password");
        }

        String jwt = jwtService.generateJWT(user);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(jwt, user.getUsername(), user.getEmail());
        logger.info("AuthService => login => User logged in successfully");
        return loginResponseDTO;
    }
    //endregion

    //region my-profile
    @Override
    public MyProfileResponseDTO getMyProfile(User user) {
        try {
            Optional<User> userDTO = userRepository.findByUsername(user.getUsername());
            if(userDTO.isPresent()) {
                List<AddressDTO> addressDTOList = userDTO.get().getAddresses().stream().map(address ->
                        new AddressDTO(
                                address.getAddressLine1(),
                                address.getAddressLine2(),
                                address.getCity(),
                                address.getCountry()
                        )
                ).toList();

                MyProfileResponseDTO profileResponseDTO = new MyProfileResponseDTO(
                        user.getUsername(),
                        user.getEmail(),
                        user.getfirstname(),
                        user.getLastname(),
                        addressDTOList
                );
                logger.info("AuthService => getMyProfile => User Data Found");
                return profileResponseDTO;
            }
            logger.error("AuthService => getMyProfile => User not found");
            throw new UserNotFoundException("User Not Found!");
        } catch (Exception e) {
            logger.error("AuthService => getMyProfile => Error: {}", e.getMessage());
            return null;
        }
    }

    private VerificationToken createVerificationToken(User user) {
        VerificationToken token = new VerificationToken();
        token.setToken(jwtService.generateVerificationJWT(user));
        token.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        token.setUser(user);
        user.getVerificationTokens().add(token);
        return token;
    }
    //endregion
}
