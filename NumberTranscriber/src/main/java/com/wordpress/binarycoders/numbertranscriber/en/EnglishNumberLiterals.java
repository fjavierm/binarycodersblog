package com.wordpress.binarycoders.numbertranscriber.en;

import com.wordpress.binarycoders.numbertranscriber.NumberLiterals;

/**
 * Nedded literal for the English version
 *
 * @author fjavierm
 */
public class EnglishNumberLiterals implements NumberLiterals {

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

    @Override
    public String[] provideUnits() {
        return UNITS;
    }

    @Override
    public String[] provideTens() {
        return TENS;
    }

    @Override
    public String provideHundred() {
        return HUNDRED;
    }

    @Override
    public String[] provideGroups() {
        return GROUPS;
    }

}
