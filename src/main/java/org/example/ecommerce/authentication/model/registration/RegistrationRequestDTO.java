package org.example.ecommerce.authentication.model.registration;

public record RegistrationRequestDTO(
        String firstname,
        String lastname,
        String email,
        String password,
        String username
) {
}
