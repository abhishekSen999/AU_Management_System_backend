package com.au.exception.customExceptions;

public class RecordNotFoundException extends RuntimeException{

	/**
	 * This Exception is raised when the no entry was found for the query.
	 */
	private static final long serialVersionUID = 1860816161506259570L;

	public RecordNotFoundException() {
		super("Records Not Found");
	}

	public RecordNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RecordNotFoundException(String message) {
		super(message);
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}

	

	
	
}
