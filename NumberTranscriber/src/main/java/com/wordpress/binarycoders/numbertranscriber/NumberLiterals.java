package com.wordpress.binarycoders.numbertranscriber;

/**
 * Interface with the methods that any new language implementation should have
 * in order to provide the transcriber the number literals of the language
 *
 * @author fjavierm
 */
public interface NumberLiterals {

    String[] provideUnits();

    String[] provideTens();

    String provideHundred();

    String[] provideGroups();
}
