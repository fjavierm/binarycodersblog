package com.wordpress.binarycoders.numbertranscriber;

import com.wordpress.binarycoders.numbertranscriber.en.EnglishTrancriber;
import com.wordpress.binarycoders.numbertranscriber.interfaces.Transcriber;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class TranscriberSelectorTest {
    
    @Test
    public void createTranscriberEnglish() {
        Transcriber transcriber = TranscriberSelector.createTranscriber(AvailableLanguages.ENGLISH);
        Assert.assertTrue(transcriber instanceof EnglishTrancriber);
    }

}
