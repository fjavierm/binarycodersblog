package com.wordpress.binarycoders.technical.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityFactory<T> {

	public ResponseEntity<T> buildOk() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<T> buildOk(T entity) {
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	public ResponseEntity<T> buildCreate(T entity) {
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}

	public ResponseEntity<T> buildBadRequest(T entity) {
		return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<T> buildNotFound(T entity) {
		return new ResponseEntity<>(entity, HttpStatus.NOT_FOUND);
	}
}
