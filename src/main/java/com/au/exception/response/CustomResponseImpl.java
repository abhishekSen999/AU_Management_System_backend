/**
 * 
 */
package com.au.exception.response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

/**
 * @author Abhishek Sen
 *
 */
public class CustomResponseImpl implements CustomResponse {

	/**
	 * 
	 */
	
	Map<String , Object> body;
	
	public CustomResponseImpl() {
		body = new HashMap<String, Object>();
		body.put("timestamp",LocalDateTime.now());
		body.put("status","");
		body.put("error","");
		body.put("message","");
	}

	@Override
	public CustomResponse setStatusInResponseBody(HttpStatus status) {
		body.put("status", status);
		return this;
	}

	@Override
	public CustomResponse setMessageInResponseBody(String message) {
		body.put("message", message);
		return this;
	}

	@Override
	public CustomResponse setError(Exception exception) {
		body.put("error", exception.getClass());
		return this;
	}

	@Override
	public Map<String, Object> getBody() {
		
		return body;
	}

}
