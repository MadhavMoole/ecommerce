package org.example.ecommerce.authentication.model;

import org.springframework.http.HttpStatus;

public record AuthServiceResponse <T>(
        HttpStatus status,
        String message,
        T data
) {
}
