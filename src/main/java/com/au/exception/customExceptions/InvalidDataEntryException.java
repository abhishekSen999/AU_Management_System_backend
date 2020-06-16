package com.au.exception.customExceptions;

public class InvalidDataEntryException extends RuntimeException {
	

	/**
	 * This Exception is raised when the sent data fir data entry or data update is invalid.s
	 */
	private static final long serialVersionUID = -89112754760964091L;

	public InvalidDataEntryException() {
	}

	public InvalidDataEntryException(String message) {
		super(message);
	}

	public InvalidDataEntryException(Throwable cause) {
		super(cause);
	}

	public InvalidDataEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataEntryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
