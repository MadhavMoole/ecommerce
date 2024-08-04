package org.example.ecommerce.exception;

public class EmailFailureException extends Exception{
    private String message;

    public EmailFailureException() {}

    public EmailFailureException(String message) {
        super(message);
        this.message = message;
    }
}
