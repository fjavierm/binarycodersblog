package com.wordpress.binarycoders.calculator.service;

/**
 *
 * @author fjavierm
 */
public enum Operator {

    ADDITION("+");

    private final String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

}
