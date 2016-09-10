package com.wordpress.binarycoders.technical.exception;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException() {
		super("Resource not found.");
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
