package com.wordpress.binarycoders.numbertranscriber.en;

import com.wordpress.binarycoders.numbertranscriber.Configuration;
import com.wordpress.binarycoders.numbertranscriber.exception.OutOfRangeException;
import com.wordpress.binarycoders.numbertranscriber.interfaces.NumberLiterals;
import com.wordpress.binarycoders.numbertranscriber.interfaces.Transcriber;
import com.wordpress.binarycoders.numbertranscriber.utils.Conversion;
import org.apache.log4j.Logger;

/**
 * English trancriber
 *
 * @author fjavierm
 */
public class EnglishTrancriber implements Transcriber {

    final static Logger logger = Logger.getLogger(EnglishTrancriber.class);

    private final NumberLiterals literals;

    public EnglishTrancriber() {

        literals = new EnglishNumberLiterals();
    }

    /**
     * Perform the transcription of a given number
     *
     * @param number Given number
     * @return literal with the number transcription
     * @throws OutOfRangeException If the number is lower or bigger that the
     * allowed values
     */
    @Override
    public String numberTranscription(long number) throws OutOfRangeException {

        logger.info(String.format("Processing number %d", number));

        checkValidRange(number);

        if (number < Configuration.BASIC_CASES) {
            return calculateBasicCases(number);
        }

        return cleanResult(transcript(String.valueOf(number), 0));
    }

    /*
     * Transforms a number in its trancription
     */
    private String transcript(String number, int position) {

        if (number.length() <= 3) {
            return transcriptAux(number);
        } else {
            return transcript(number.substring(0, number.length() - 3), ++position)
                    + " " + checkPosition(position) + " "
                    + transcriptAux(number.substring(number.length() - 3, number.length()));
        }
    }

    /*
     * Transforms groups of three numbers
     */
    private String transcriptAux(String number) {

        StringBuilder sb = new StringBuilder();
        int numericNumber = Integer.valueOf(number);

        if (numericNumber / 100 > 0) {
            appendSpace(sb);
            sb.append(literals.provideUnits()[numericNumber / 100])
                    .append(" ")
                    .append(literals.provideHundred());

            numericNumber = numericNumber % 100;
        }

        if (numericNumber / 10 > 0) {
            appendSpace(sb);

            if (numericNumber < Configuration.BASIC_CASES) {
                sb.append(calculateBasicCases(numericNumber));
            } else {
                sb.append(literals.provideTens()[numericNumber / 10]);
                numericNumber = numericNumber % 10;
                appendSpace(sb);
                sb.append(calculateBasicCases(numericNumber));
            }
        } else {
            sb.append(calculateBasicCases(numericNumber));
        }

        return sb.toString();
    }

    /*
     * Return group number based on position
     */
    private String checkPosition(int position) {

        return literals.provideGroups()[position];
    }

    /*
     * Add a new space to a string builder if it is not empty
     */
    private void appendSpace(StringBuilder sb) {

        if (!sb.toString().isEmpty()) {
            sb.append(" ");
        }
    }

    /*
     * Validates our application useful range
     */
    private void checkValidRange(long number) throws OutOfRangeException {

        if (number < Configuration.MIN_VALID_RANGE || number > Configuration.MAX_VALID_RANGE) {
            throw new OutOfRangeException("The value is out of the allowed range ["
                    + Configuration.MIN_VALID_RANGE + "-" + Configuration.MAX_VALID_RANGE + "]");
        }
    }

    /*
     * Retunr the literal for numbers less than 20
     */
    private String calculateBasicCases(long number) {

        return number > 0 ? literals.provideUnits()[Conversion.safeLongToInt(number)] : "";
    }

    /*
     * Remove initial and end spaces and replace more than one spaces together
     * by just one
     */
    private String cleanResult(String number) {
        return number.trim().replaceAll("\\s{2,}", " ");
    }

    public NumberLiterals getLiterals() {
        return literals;
    }

    public void setLiterals(NumberLiterals literals) {
        throw new UnsupportedOperationException();
    }

}
