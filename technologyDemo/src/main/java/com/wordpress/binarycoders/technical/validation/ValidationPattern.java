package com.wordpress.binarycoders.technical.validation;

public interface ValidationPattern {

	interface Email {

		String VALID = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	}
}
