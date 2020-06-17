package com.au.exception.controllerAdvisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.au.exception.customExceptions.FailedDatabaseLoggingException;
import com.au.exception.customExceptions.InvalidDataEntryException;
import com.au.exception.customExceptions.InvalidQueryDataException;
import com.au.exception.customExceptions.RecordNotFoundException;
import com.au.exception.customExceptions.UnauthorizedUserException;
import com.au.exception.response.CustomResponse;
import com.au.exception.response.CustomResponseImpl;


@Order(Ordered.HIGHEST_PRECEDENCE)	
@ControllerAdvice
public class PrimaryControllerAdvisor extends ResponseEntityExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(PrimaryControllerAdvisor.class);

	@ExceptionHandler(FailedDatabaseLoggingException.class)
	public ResponseEntity<Object> handleFailedDatabaseLoggingException( FailedDatabaseLoggingException exception, WebRequest request )
	{
		logger.error("For some unknows reason, database acces logs cannot be set. Exception raised: "+exception.getMessage()
						,exception);
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	
	
	@ExceptionHandler(InvalidDataEntryException.class)
	public ResponseEntity<Object> handleInvalidDataEntryException( InvalidDataEntryException exception, WebRequest request )
	{
		
		logger.info("Data entry failed because of: "+exception.getMessage());
		
		HttpStatus status = HttpStatus.EXPECTATION_FAILED;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(InvalidQueryDataException.class)
	public ResponseEntity<Object> handleInvalidQueryDataException( InvalidQueryDataException exception, WebRequest request )
	{
		logger.info("Data Query failed because of: "+exception.getMessage());
		
		HttpStatus status = HttpStatus.EXPECTATION_FAILED;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException( RecordNotFoundException exception, WebRequest request )
	{
		logger.info("Data Query failed because of: "+exception.getMessage());
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(UnauthorizedUserException.class)
	public ResponseEntity<Object> handleUnauthorizedUserException( UnauthorizedUserException exception, WebRequest request )
	{
		logger.warn("Unauthorized access attempt: "+exception.getMessage());
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		CustomResponse response = new CustomResponseImpl();
		response.setStatusInResponseBody(status)
				.setError(exception)
				.setMessageInResponseBody(exception.getMessage());
		return handleExceptionInternal(exception, response.getBody(), new HttpHeaders(), status, request);
	}
	
	
	
	
	
	
}
