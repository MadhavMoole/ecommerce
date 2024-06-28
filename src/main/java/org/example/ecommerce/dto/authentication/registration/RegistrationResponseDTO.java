package org.example.ecommerce.dto.authentication.registration;

public record RegistrationResponseDTO(
        String jwt,
        String username,
        String email
) {
}
