package com.wordpress.binarycoders.technical.exception;

public class InvalidInputDataException extends Exception {

	public InvalidInputDataException() {
		super("Invalid input data.");
	}

	public InvalidInputDataException(final String message) {
		super(message);
	}
}
