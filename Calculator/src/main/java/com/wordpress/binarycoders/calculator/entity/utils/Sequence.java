package com.wordpress.binarycoders.calculator.entity.utils;

public final class Sequence {
    
    private Sequence()  {}
	
    public static final String GENERATOR = "EVENT_GEN";
    public static final String TABLE = "dbsequence";
    public static final String ID_COLUMN_NAME = "id";
    public static final String LAST_VALUE_COLUMN_NAME = "lastValue";
}
