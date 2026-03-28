package com.example.demo.advices;

import java.time.LocalDateTime;

public class ApiResponse<T> {
	
	private LocalDateTime timestamp;
	
	private ApiError apiError;
	
	private T data;
	
	public ApiResponse() {
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiResponse(T data) {
		this();
		this.data = data;
	}
	
	public ApiResponse(ApiError apiError) {
		this();
		this.apiError = apiError;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ApiError getApiError() {
		return apiError;
	}

	public void setApiError(ApiError apiError) {
		this.apiError = apiError;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
