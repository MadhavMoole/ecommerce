package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.User;

public record UserDTO(
        String username,
        String email,
        String firstname,
        String lastname
) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getfirstname(),
                user.getLastname()
        );
    }
}
