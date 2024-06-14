package org.example.ecommerce.authentication.model.Registration;

public record RegistrationRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String userName
) {
}
