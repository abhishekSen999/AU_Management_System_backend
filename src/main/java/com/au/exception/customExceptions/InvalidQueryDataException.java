package com.au.exception.customExceptions;

public class InvalidQueryDataException extends RuntimeException {

	/**
	 * This exception is raised when the parameters for the query are invalid
	 * Example : Key for any table cannot be 0;
	 * query where onb_id = 0 is an invalid query data 
	 */
	private static final long serialVersionUID = 9147737525310481194L;

	public InvalidQueryDataException() {
		super(" Invalid data for querying ");
	}

	public InvalidQueryDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidQueryDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidQueryDataException(String message) {
		super(message);
	}

	public InvalidQueryDataException(Throwable cause) {
		super(cause);
	}

}
