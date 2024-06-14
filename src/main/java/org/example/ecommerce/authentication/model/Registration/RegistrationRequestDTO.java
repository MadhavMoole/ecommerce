package org.example.ecommerce.authentication.model.Registration;

public record RegistrationRequestDTO(
        String firstname,
        String lastname,
        String email,
        String password,
        String username
) {
}
