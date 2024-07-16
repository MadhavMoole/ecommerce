package org.example.ecommerce.exception;

public class UserAlreadyExistsException extends RuntimeException{
    
    private String message;

    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
