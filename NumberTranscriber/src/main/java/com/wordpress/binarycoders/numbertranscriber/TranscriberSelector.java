package com.wordpress.binarycoders.numbertranscriber;

import com.wordpress.binarycoders.numbertranscriber.en.EnglishTrancriber;

/**
 * Transcriber's parametrized factory
 *
 * @author fjavierm
 */
public class TranscriberSelector {

    private final static String TRANCRIBER_LANGUAGE_UNNAVAILABLE
            = "Transcriber is not available in this language.";

    public static Transcriber createTranscriber(AvailableLanguages language)
            throws UnsupportedOperationException {

        Transcriber trancriber = null;

        switch (language) {
            case ENGLISH:
                trancriber = new EnglishTrancriber();
                break;

            default:
                throw new UnsupportedOperationException(TRANCRIBER_LANGUAGE_UNNAVAILABLE);
        }

        return trancriber;
    }
}
