package com.cognixia.jump.exception;

public class InvalidDataInputException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDataInputException(String message) {
		super(message);
	}

	public InvalidDataInputException(String message, Throwable cause) {
		super(message, cause);
	}
}
