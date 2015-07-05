package com.wordpress.binarycoders.calculator.service;

import java.util.List;

import com.wordpress.binarycoders.calculator.model.BinaryOperationData;

/**
 *
 * @author fjavierm
 */
public interface OperationService {

    Integer perform(BinaryOperationData operationData);

    List<Integer> performMultiple(List<BinaryOperationData> operationData);

    List<Integer> performMultipleHeavy(List<BinaryOperationData> operationData);

}
