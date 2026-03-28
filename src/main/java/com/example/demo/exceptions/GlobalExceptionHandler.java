package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.advices.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<ApiError> handleBadRequestException(ResourceAlreadyExists exception) {
		ApiError apiError = new ApiError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setMessage(exception.getMessage());
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleInternalServerException(Exception exception) {
		
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
