package com.cognixia.jump.exception;

public class ResouceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ResouceNotFoundException(String message) {
		super(message);
	}
	
	public ResouceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
