package com.wordpress.binarycoders.calculator.dao;

import com.wordpress.binarycoders.calculator.entity.OperationCached;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author fjavierm
 */
@Stateless
public class JpaOperationCachedDao extends JpaGenericDao<OperationCached, Integer> implements OperationCachedDao {

    @Override
    public Integer checkStoredResult(final String checkSum) {

        final TypedQuery<Integer> query = this.em.createNamedQuery(OperationCached.CHECK_STORED_RESULT, Integer.class);
        query.setParameter("checkSum", checkSum);

        return query.getSingleResult();
    }

}
