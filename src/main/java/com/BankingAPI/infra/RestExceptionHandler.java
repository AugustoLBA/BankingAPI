package com.BankingAPI.infra;

import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.PasswordInvalidException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, "Validation Error", errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> paswordInvalidException(PasswordInvalidException ex){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> usernameUniqueViolationException(UsernameUniqueViolationException ex){
        ErrorMessage errorMessage= new ErrorMessage(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }
}
