package org.example.ecommerce.exception.controlleradvice;

import org.example.ecommerce.controllers.UserController;
import org.example.ecommerce.exception.NoAddressFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerAdvice {
    
    @ExceptionHandler(NoAddressFoundException.class)
    public ResponseEntity<String> handleNoAddressFoundException(NoAddressFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
