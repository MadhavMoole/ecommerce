package org.example.ecommerce.exception;

public class InvalidCredentialException extends Exception{
    
    private String message;

    public InvalidCredentialException() {}

    public InvalidCredentialException(String message) {
        super(message);
        this.message = message;
    }
}
