package com.wordpress.binarycoders.technical.domain;

public interface Transformer<T, R> {

	T toDto(R entity);

	R toEntity(T dto);
}
