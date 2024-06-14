package org.example.ecommerce.authentication.model.Registration;

public record RegistrationResponseDTO(
        String jwt,
        String username,
        String email
) {
}
