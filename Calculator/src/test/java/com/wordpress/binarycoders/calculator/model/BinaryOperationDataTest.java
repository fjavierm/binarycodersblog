package com.wordpress.binarycoders.calculator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class BinaryOperationDataTest {

    private BinaryOperationData data;

    @Before
    public void setUp() {

        this.data = new BinaryOperationData();
    }

    @Test
    public void getAndSetFirstOperand() {

        this.data.setFirstOperand(Integer.MIN_VALUE);

        Assert.assertNotNull(this.data.getFirstOperand());
    }

    @Test
    public void getAndSetSecondOperand() {

        this.data.setSecondOperand(Integer.MIN_VALUE);

        Assert.assertNotNull(this.data.getSecondOperand());
    }
}
