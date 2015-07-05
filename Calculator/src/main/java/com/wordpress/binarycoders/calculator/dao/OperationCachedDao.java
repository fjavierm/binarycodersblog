package com.wordpress.binarycoders.calculator.dao;

import com.wordpress.binarycoders.calculator.entity.OperationCached;

/**
 *
 * @author fjavierm
 */
public interface OperationCachedDao extends GenericDao<OperationCached, Integer> {

    Integer checkStoredResult(String checkSum);

}
