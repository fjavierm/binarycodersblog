package com.wordpress.binarycoders.calculator.rest.common;

import java.io.Serializable;

/**
 *
 * @author fjavierm
 * @param <T>
 */
public class DataResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String identifier;
    private T result;

    public DataResponse() {

    }

    public DataResponse(final String identifier, final T result) {

        this.identifier = identifier;
        this.result = result;
    }

    public String getIdentifier() {

        return this.identifier;
    }

    public void setIdentifier(final String identifier) {

        this.identifier = identifier;
    }

    public T getResult() {

        return this.result;
    }

    public void setResult(final T result) {

        this.result = result;
    }

}
