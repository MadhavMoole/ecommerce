package org.example.ecommerce.authentication.model;

public record RegistrationDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String userName
) {
}
