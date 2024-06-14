package org.example.ecommerce.authentication.model.Login;

public record LoginResponseDTO(
        String jwt,
        String username,
        String email
) {
}
