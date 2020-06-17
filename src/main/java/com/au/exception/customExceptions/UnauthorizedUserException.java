package com.au.exception.customExceptions;

public class UnauthorizedUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6355977100103063779L;

	public UnauthorizedUserException() {
		super("Unauthorized User");
	}

	public UnauthorizedUserException(String message) {
		super(message);
	}

	public UnauthorizedUserException(Throwable cause) {
		super(cause);
	}

	public UnauthorizedUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
