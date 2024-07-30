package org.example.ecommerce.exception;

public class UserNotVerifiedException extends Exception{
    
    private String message;

    public UserNotVerifiedException() {}

    public UserNotVerifiedException(String message) {
        super(message);
        this.message = message;
    }
}
