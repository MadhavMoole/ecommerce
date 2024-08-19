package org.example.ecommerce.exception;

public class EmailNotFoundException extends Exception{
    private String message;

    public EmailNotFoundException() {}

    public EmailNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
