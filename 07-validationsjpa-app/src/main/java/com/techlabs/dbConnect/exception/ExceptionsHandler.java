package com.techlabs.dbConnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.techlabs.dbConnect.errors.StudentError;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<StudentError> handleStudentNotFoundException(StudentNotFoundException exception){
		StudentError error = new StudentError();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<StudentError> handleStudentException(MethodArgumentTypeMismatchException exception){
		StudentError error = new StudentError();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage("Please Enter Roll Number to get Student Data");
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StudentError> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    StudentError error = new StudentError();
	    error.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    error.setTimeStamp(System.currentTimeMillis());
	    
	    // Extract validation errors
	    StringBuilder errorMessage = new StringBuilder("Validation failed for fields: ");
	    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
	        errorMessage.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append("; ");
	    }
	    error.setErrorMessage(errorMessage.toString());
	    
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}


