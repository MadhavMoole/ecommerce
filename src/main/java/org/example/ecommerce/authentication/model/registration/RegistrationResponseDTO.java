package org.example.ecommerce.authentication.model.registration;

public record RegistrationResponseDTO(
        String jwt,
        String username,
        String email
) {
}
