package com.wordpress.binarycoders.numbertranscriber.en;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class EnglishNumberLiteralTest {
    
    EnglishNumberLiterals literals;
    
    @Before
    public void setUp() {
        literals = new EnglishNumberLiterals();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void provideUnits() {
        Assert.assertTrue("one".equals(literals.provideUnits()[1]));
    }

    @Test
    public void provideTens() {
        Assert.assertTrue("twenty".equals(literals.provideTens()[2]));
    }

    @Test
    public void provideHundred() {
        Assert.assertTrue("hundred".equals(literals.provideHundred()));
    }

    @Test
    public void provideGroups() {
        Assert.assertTrue("thousand".equals(literals.provideGroups()[1]));
    }
}
