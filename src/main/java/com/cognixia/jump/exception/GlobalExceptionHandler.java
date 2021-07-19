package com.cognixia.jump.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<?> resourceNotFound(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(404).body(errorDetails);
	}

	@ExceptionHandler(InvalidDataInputException.class)
	public ResponseEntity<?> invalidDataInput(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return ResponseEntity.status(422).body(errorDetails);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
