package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.advices.ApiError;
import com.example.demo.advices.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceAlreadyExists.class)
	public ResponseEntity<ApiResponse<?>> handleBadRequestException(ResourceAlreadyExists exception) {
		ApiError apiError = new ApiError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setMessage(exception.getMessage());
		
		return buildErrorResponse(apiError);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleInternalServerException(Exception exception) {
		
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return buildErrorResponse(apiError);
	}
	
	
	public ResponseEntity<ApiResponse<?>> buildErrorResponse(ApiError apiError) {
		
		// Convert the apiError - to API - Response
		return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
		
	}
	
}
