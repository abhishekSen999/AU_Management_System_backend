package com.au.exception.controllerAdvisors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.au.exception.response.CustomResponse;
import com.au.exception.response.CustomResponseImpl;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class DefaultControllerAdvisor extends ResponseEntityExceptionHandler {

	public DefaultControllerAdvisor() {
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request)
	{
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	
	
	
	
	
	
	

}
