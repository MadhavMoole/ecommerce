package org.example.ecommerce.exception;

public class TokenNotFoundException extends Exception{
    private String message;

    public TokenNotFoundException() {}

    public TokenNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
