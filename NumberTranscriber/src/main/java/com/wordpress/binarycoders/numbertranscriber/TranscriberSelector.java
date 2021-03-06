package com.wordpress.binarycoders.numbertranscriber;

import com.wordpress.binarycoders.numbertranscriber.en.EnglishNumberLiterals;
import com.wordpress.binarycoders.numbertranscriber.en.EnglishTrancriber;
import com.wordpress.binarycoders.numbertranscriber.interfaces.Transcriber;
import org.apache.log4j.Logger;

/**
 * Transcriber's parametrized factory
 *
 * @author fjavierm
 */
public class TranscriberSelector {

    final static Logger logger = Logger.getLogger(TranscriberSelector.class);

    private final static String TRANCRIBER_LANGUAGE_UNNAVAILABLE
            = "Transcriber is not available in this language.";

    public static Transcriber createTranscriber(AvailableLanguages language)
            throws UnsupportedOperationException {

        logger.info(String.format("Creating trancriber for language %s", language.name()));

        Transcriber trancriber = null;

        switch (language) {
            case ENGLISH:
                trancriber = new EnglishTrancriber(new EnglishNumberLiterals());
                break;

            default:
                throw new UnsupportedOperationException(TRANCRIBER_LANGUAGE_UNNAVAILABLE);
        }

        return trancriber;
    }
}
