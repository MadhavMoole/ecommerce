package org.example.ecommerce.authentication.service;

import org.example.ecommerce.authentication.model.RegistrationDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<String> createUser(RegistrationDTO registrationDTO);
}
