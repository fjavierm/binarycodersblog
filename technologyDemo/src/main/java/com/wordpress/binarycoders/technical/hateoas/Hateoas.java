package com.wordpress.binarycoders.technical.hateoas;

import java.util.List;

public interface Hateoas<T> {

	void addLinks(List<T> objects);

	void addLinks(T objects) ;
}
