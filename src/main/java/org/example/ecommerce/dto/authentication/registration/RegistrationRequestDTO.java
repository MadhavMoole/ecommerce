package org.example.ecommerce.dto.authentication.registration;

public record RegistrationRequestDTO(
        String firstname,
        String lastname,
        String email,
        String password,
        String username
) {
}
