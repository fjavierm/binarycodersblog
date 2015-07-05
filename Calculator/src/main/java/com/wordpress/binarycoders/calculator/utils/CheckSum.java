package com.wordpress.binarycoders.calculator.utils;

import com.wordpress.binarycoders.calculator.model.BinaryOperationData;
import com.wordpress.binarycoders.calculator.service.Operator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fjavierm
 */
public class CheckSum {

    private static final Logger logger = LoggerFactory.getLogger(CheckSum.class);

    public static final String MD5 = "MD5";

    public static String calculate(final BinaryOperationData operationData, Operator operator) {

        final StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(operationData.getFirstOperand()))
                .append(operator.getOperator())
                .append(String.valueOf(operationData.getSecondOperand()));

        try {
            final MessageDigest md = MessageDigest.getInstance(CheckSum.MD5);

            return CheckSum.byteArray2Hex(md.digest(sb.toString().getBytes()));
        } catch (final NoSuchAlgorithmException ex) {
            CheckSum.logger.error("Fail generation checkSum -> {}", ex.getMessage());
        }

        return "";
    }

    private static String byteArray2Hex(final byte[] hash) {

        String result = "";

        try (Formatter formatter = new Formatter()) {

            for (final byte b : hash) {
                formatter.format("%02x", b);
            }

            result = formatter.toString();
        }

        return result;
    }
}
