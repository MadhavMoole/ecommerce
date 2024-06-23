package org.example.ecommerce.authentication.model.login;

public record LoginResponseDTO(
        String jwt,
        String username,
        String email
) {
}
