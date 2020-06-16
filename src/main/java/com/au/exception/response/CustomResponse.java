package com.au.exception.response;

import java.util.Map;

import org.springframework.http.HttpStatus;

public interface CustomResponse {
	
	public CustomResponse setStatusInResponseBody(HttpStatus status);
	
	public CustomResponse setMessageInResponseBody(String message);
	
	public CustomResponse setError(Exception exception);
	
	public Map<String,Object> getBody();
	

}
