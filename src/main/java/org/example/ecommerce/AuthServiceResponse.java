package org.example.ecommerce;

import org.springframework.http.HttpStatus;

public record AuthServiceResponse <T>(
        HttpStatus status,
        String message,
        T data
) {
}
