package com.wordpress.binarycoders.calculator.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wordpress.binarycoders.calculator.model.BinaryOperationData;
import com.wordpress.binarycoders.calculator.service.Operator;

public class CheckSumTest {

    private BinaryOperationData data1;
    private BinaryOperationData data2;

    @Before
    public void setUp() {

        this.data1 = new BinaryOperationData();
        this.data1.setFirstOperand(1);
        this.data1.setSecondOperand(2);

        this.data2 = new BinaryOperationData();
        this.data2.setFirstOperand(3);
        this.data2.setSecondOperand(4);
    }

    @Test
    public void calculateOne() {

        Assert.assertTrue("c22edbd0ac0f827509d95355a6bbb767".equals(CheckSum.calculate(this.data1, Operator.ADDITION)));
    }

    @Test
    public void calculateTwo() {

        Assert.assertTrue("49f990c30b4bbb32b123df12c5cfc462".equals(CheckSum.calculate(this.data2, Operator.ADDITION)));
    }
}
