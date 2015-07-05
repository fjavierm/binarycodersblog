package com.wordpress.binarycoders.calculator.service;

import com.wordpress.binarycoders.calculator.dao.OperationCachedDao;
import com.wordpress.binarycoders.calculator.entity.OperationCached;
import com.wordpress.binarycoders.calculator.model.BinaryOperationData;
import com.wordpress.binarycoders.calculator.utils.CheckSum;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fjavierm
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AdditionOperationService implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(AdditionOperationService.class);

    @Inject
    private OperationCachedDao cache;

    @Override
    public Integer perform(final BinaryOperationData operationData) {

        AdditionOperationService.logger.info("Performing addition operation");

        return operationData.getFirstOperand() + operationData.getSecondOperand();
    }

    @Override
    public List<Integer> performMultiple(final List<BinaryOperationData> operationData) {

        final List<Integer> result = new ArrayList<>();

        AdditionOperationService.logger.info("Performing multiple addition operation");

        operationData.stream().forEach((data) -> {
            result.add(this.perform(data));
        });

        return result;
    }

    @Override
    public List<Integer> performMultipleHeavy(final List<BinaryOperationData> operationData) {

        final List<Integer> result = new ArrayList<>();

        AdditionOperationService.logger.info("Performing multiple heavy addition operation");

        operationData.stream().forEach((data) -> {
            result.add(this.performWithCache(data));
        });

        return result;
    }

    private Integer performWithCache(final BinaryOperationData operationData) {

        AdditionOperationService.logger.info("Performing multiple heavy addition operation with cache");

        final String checkSum = CheckSum.calculate(operationData, Operator.ADDITION);

        if (!checkSum.isEmpty()) {
            try {
                return this.cache.checkStoredResult(checkSum);
            } catch (final NoResultException ex) {
                AdditionOperationService.logger.warn("Element {} + {} not found in the cache",
                        operationData.getFirstOperand(), operationData.getSecondOperand());
            }
        }

        final Integer result = this.perform(operationData);

        if (!checkSum.isEmpty()) {
            this.storeInCache(checkSum, result);
        }

        return result;
    }

    private void storeInCache(final String checkSum, final Integer result) {

        final OperationCached cacheInfo = new OperationCached();
        cacheInfo.setCheckSum(checkSum);
        cacheInfo.setOperationResult(result);

        this.cache.persist(cacheInfo);
    }

}
