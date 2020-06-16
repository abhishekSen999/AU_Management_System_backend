package com.au.exception.customExceptions;

public class FailedDatabaseLoggingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5062472799568212388L;

	public FailedDatabaseLoggingException() {
	}

	public FailedDatabaseLoggingException(String message) {
		super(message);

	}

	public FailedDatabaseLoggingException(Throwable cause) {
		super(cause);
	}

	public FailedDatabaseLoggingException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailedDatabaseLoggingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
