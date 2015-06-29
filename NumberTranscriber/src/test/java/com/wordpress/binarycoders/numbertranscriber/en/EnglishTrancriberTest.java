package com.wordpress.binarycoders.numbertranscriber.en;

import com.wordpress.binarycoders.numbertranscriber.AvailableLanguages;
import com.wordpress.binarycoders.numbertranscriber.Transcriber;
import com.wordpress.binarycoders.numbertranscriber.TranscriberSelector;
import com.wordpress.binarycoders.numbertranscriber.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class EnglishTrancriberTest {

    private Transcriber transcriber;

    @Before
    public void setUp() {
        transcriber = TranscriberSelector.createTranscriber(AvailableLanguages.ENGLISH);
    }

    @Test(expected = OutOfRangeException.class)
    public void numberTranscriptionOufOfRangeDown() throws OutOfRangeException {
        transcriber.numberTranscription(Long.valueOf("0"));

        Assert.fail("An exception should be launched.");
    }

    @Test(expected = OutOfRangeException.class)
    public void numberTranscriptionOufOfRangeUp() throws OutOfRangeException {
        transcriber.numberTranscription(Long.valueOf("9999999999"));

        Assert.fail("An exception should be launched.");
    }

    @Test
    public void numberTranscriptionBasic() throws OutOfRangeException {
        String number = transcriber.numberTranscription(Long.valueOf("20"));

        Assert.assertTrue("twenty".equals(number));
    }

    @Test
    public void numberTranscriptionHundred() throws OutOfRangeException {
        String number = transcriber.numberTranscription(Long.valueOf("133"));

        Assert.assertTrue("one hundred thirty three".equals(number));
    }

    @Test
    public void numberTranscriptionThousand() throws OutOfRangeException {
        String number = transcriber.numberTranscription(Long.valueOf("1034"));

        Assert.assertTrue("one thousand thirty four".equals(number));
    }

    @Test
    public void numberTranscriptionHundredThousand() throws OutOfRangeException {
        String number = transcriber.numberTranscription(Long.valueOf("189034"));

        Assert.assertTrue("one hundred eighty nine thousand thirty four".equals(number));
    }

    @Test
    public void numberTranscriptionMillionHundredThousand() throws OutOfRangeException {
        String number = transcriber.numberTranscription(Long.valueOf("726875345"));

        Assert.assertTrue("seven hundred twenty six million eight hundred seventy five thousand three hundred forty five".equals(number));
    }
}
