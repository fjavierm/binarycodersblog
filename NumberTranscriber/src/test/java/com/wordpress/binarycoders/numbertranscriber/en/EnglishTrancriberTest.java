package com.wordpress.binarycoders.numbertranscriber.en;

import com.wordpress.binarycoders.numbertranscriber.exception.OutOfRangeException;
import com.wordpress.binarycoders.numbertranscriber.interfaces.Transcriber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author fjavierm
 */
@RunWith(MockitoJUnitRunner.class)
public class EnglishTrancriberTest {

    private static final String[] UNITS = {
        "",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };

    private static final String[] TENS = {
        "",
        "",
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety"
    };

    private static final String[] GROUPS = {"",
        "thousand",
        "million"
    };

    private static final String HUNDRED = "hundred";

    @Mock
    EnglishNumberLiterals literals;

    Transcriber transcriber;

    @Before
    public void setUp() {
        transcriber = new EnglishTrancriber(literals);

        Mockito.when(literals.provideUnits()).thenReturn(UNITS);
        Mockito.when(literals.provideTens()).thenReturn(TENS);
        Mockito.when(literals.provideHundred()).thenReturn(HUNDRED);
        Mockito.when(literals.provideGroups()).thenReturn(GROUPS);
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
