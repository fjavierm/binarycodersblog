package com.wordpress.binarycoders.numbertranscriber.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class ConversionTest {

    @Test(expected = IllegalArgumentException.class)
    public void safeLongToIntLowerRangeViolation() {
        Conversion.safeLongToInt(Long.valueOf("-99999999999999999999"));

        Assert.fail("An exception should be launched.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void safeLongToIntUpperRangeViolation() {
        Conversion.safeLongToInt(Long.valueOf("99999999999999999999"));

        Assert.fail("An exception should be launched.");
    }

    @Test
    public void safeLongToIntSuccess() {
        int number = Conversion.safeLongToInt(Long.valueOf("54"));

        Assert.assertTrue(number == 54);
    }

}
