package org.example.ecommerce.service;

import java.util.regex.Pattern;

public class ValidationService {

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean isValidPassword(String password) {
        String regexPattern = "^(?=(.*[a-z]){3,})(?=(.*[A-Z]){2,})(?=(.*[0-9]){2,})(?=(.*[!@#$%^&*()\\-__+.]){1,}).{8,}$";
        return Pattern.compile(regexPattern)
                .matcher(password)
                .matches();
    }
}
