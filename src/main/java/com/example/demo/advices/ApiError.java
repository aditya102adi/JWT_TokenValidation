package com.example.demo.advices;

import org.springframework.http.HttpStatus;

public class ApiError {
	
	String message;
	
	HttpStatus status;
	
	public ApiError() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ApiError(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}


}

