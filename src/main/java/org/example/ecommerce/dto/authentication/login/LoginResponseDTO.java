package org.example.ecommerce.dto.authentication.login;

public record LoginResponseDTO(
        String jwt,
        String username,
        String email
) {
}
