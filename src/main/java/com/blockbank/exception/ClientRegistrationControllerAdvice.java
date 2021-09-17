package com.blockbank.exception;

/**
 * @author Alex Shijan
 */

import com.blockbank.contoller.ClientRegistrationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(assignableTypes = ClientRegistrationController.class)
public class ClientRegistrationControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> illegalArguementException(IllegalArgumentException exception) {
        return ResponseEntity.status(400).body(exception.getMessage());
    }
}
