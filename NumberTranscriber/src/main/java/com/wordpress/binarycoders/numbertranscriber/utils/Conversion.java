package com.wordpress.binarycoders.numbertranscriber.utils;

/**
 * Conversions utils class
 *
 * @author fjavierm
 */
public class Conversion {

    /**
     * Converts a given long to int in a safe way
     *
     * @param l long number
     * @return integer number
     */
    public static int safeLongToInt(long l) throws IllegalArgumentException {

        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast");
        }

        return (int) l;
    }
}
