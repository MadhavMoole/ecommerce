package org.example.ecommerce.authentication.service;

import org.example.ecommerce.authentication.model.RegistrationDTO;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{

    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> createUser(RegistrationDTO registrationDTO) {

        if(!Validator.isValidEmail(registrationDTO.email())) {
            logger.error("AuthService => createUser => Error: Invalid Email");
            return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
        }

        if(!Validator.isValidPassword(registrationDTO.password())) {
            logger.error("AuthService => createUser => Error: Invalid Password");
            return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByUsername(registrationDTO.userName()).isPresent()) {
            logger.error("AuthService => createUser => Error: User Already exists");
            return new ResponseEntity<>("User Already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByEmail(registrationDTO.email()).isPresent()) {
            logger.error("AuthService => createUser => Error: Email Already taken");
            return new ResponseEntity<>("Email Already taken", HttpStatus.BAD_REQUEST);
        }

        try {
            User user = new User();
            user.setUsername(registrationDTO.userName());
            user.setFirstName(registrationDTO.firstName());
            user.setLastName(registrationDTO.lastName());
            user.setEmail(registrationDTO.email());
            user.setPassword(registrationDTO.password());
            userRepository.save(user);
            logger.info("AuthService => createUser => User created");
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        } catch (DataAccessException e) {
            logger.error("AuthService => createUser => Error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }

}
