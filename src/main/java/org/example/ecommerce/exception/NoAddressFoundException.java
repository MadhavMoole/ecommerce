package org.example.ecommerce.exception;

public class NoAddressFoundException extends Exception {
    private String message;

    public NoAddressFoundException() {}

    public NoAddressFoundException(String message) {
        super(message);
        this.message = message;
    }
}
