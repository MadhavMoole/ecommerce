package org.example.ecommerce.dto.authentication.registration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record RegistrationRequestDTO(
        String firstname,
        String lastname,
        @Email
        String email,
        @Valid
        String password,
        String username
) {
}
