package com.techlabs.dbConnect.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.techlabs.dbConnect.errors.ErrorResponse;
import com.techlabs.dbConnect.errors.Field;
import com.techlabs.dbConnect.errors.FieldError;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<String> handleUserApiException(UserApiException ex) {
        // Return the exception message directly
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldError> methodArgsException(MethodArgumentNotValidException exception){
        FieldError fieldError = new FieldError();
        List<Field> fields = new ArrayList<>();
        
        exception.getBindingResult().getFieldErrors().forEach(
                error -> {
                    fields.add(new Field(error.getField(), error.getDefaultMessage()));
                }
        );
        
        fieldError.setMessage(fields);
        fieldError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        fieldError.setTimestamp(new Date());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldError);
    }

//    // Handle any other exceptions globally
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException1(Exception ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
    
 // Handling generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            new Date()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handling other specific exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            new Date()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Null value encountered in your request",
            new Date()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
