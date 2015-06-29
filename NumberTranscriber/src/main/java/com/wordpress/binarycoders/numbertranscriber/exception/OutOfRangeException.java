package com.wordpress.binarycoders.numbertranscriber.exception;

/**
 *
 * @author fjavierm
 */
public class OutOfRangeException extends Exception {

    public OutOfRangeException(String message) {
        super(message);
    }

    public OutOfRangeException(Throwable throwable) {
        super(throwable);
    }

    public OutOfRangeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
