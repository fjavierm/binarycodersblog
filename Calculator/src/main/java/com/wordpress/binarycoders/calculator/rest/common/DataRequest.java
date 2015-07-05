package com.wordpress.binarycoders.calculator.rest.common;

import java.io.Serializable;

/**
 *
 * @author fjavierm
 * @param <T>
 */
public class DataRequest<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String identifier;
    private T data;

    public DataRequest() {

    }

    public DataRequest(final String identifier, final T data) {

        this.identifier = identifier;
        this.data = data;
    }

    public String getIdentifier() {

        return this.identifier;
    }

    public void setIdentifier(final String identifier) {

        this.identifier = identifier;
    }

    public T getData() {

        return this.data;
    }

    public void setData(final T data) {

        this.data = data;
    }

}
