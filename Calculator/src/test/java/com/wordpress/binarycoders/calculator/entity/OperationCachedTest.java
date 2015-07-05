package com.wordpress.binarycoders.calculator.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class OperationCachedTest {

    private OperationCached cache;

    @Before
    public void setUp() {

        this.cache = new OperationCached();
    }

    @Test
    public void getAndSetCheckSum() {

        this.cache.setCheckSum("");

        Assert.assertNotNull(this.cache.getCheckSum());
    }

    @Test
    public void getAndSetOperationResult() {

        this.cache.setOperationResult(Integer.MIN_VALUE);

        Assert.assertNotNull(this.cache.getOperationResult());
    }
}
