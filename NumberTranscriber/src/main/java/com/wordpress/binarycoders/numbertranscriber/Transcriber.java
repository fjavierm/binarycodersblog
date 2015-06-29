package com.wordpress.binarycoders.numbertranscriber;

import com.wordpress.binarycoders.numbertranscriber.exception.OutOfRangeException;

/**
 * Transcriber interface, any new language trancriber should implement it.
 *
 * @author fjavierm
 */
public interface Transcriber {

    String numberTranscription(long number) throws OutOfRangeException;
}
