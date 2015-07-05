package com.wordpress.binarycoders.calculator.dao;

import com.wordpress.binarycoders.calculator.entity.OperationCached;
import com.wordpress.binarycoders.calculator.test.AbstractPersistenceTest;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fjavierm
 */
public class JpaOperationCachedDaoTest extends AbstractPersistenceTest {

    private JpaOperationCachedDao dao;

    @Before
    public void setUp() throws Exception {

        this.dao = this.entityManagerInjector(new JpaOperationCachedDao());
    }

    @Test
    public void findById() {

        final OperationCached op = this.dao.findById(1);

        Assert.assertNotNull(op);
    }

    @Test
    public void findByAll() {

        final List<OperationCached> ops = this.dao.findAll();

        Assert.assertNotNull(ops);
        Assert.assertTrue(ops.size() > 1);
    }

    @Test
    public void countAll() {

        final Long rows = this.dao.countAll();

        Assert.assertNotNull(rows);
        Assert.assertTrue(rows > 1);
    }

    @Test
    public void persist() {

        final OperationCached op = new OperationCached();
        op.setCheckSum("ffff");
        op.setOperationResult(50);

        this.startTransaction();
        this.dao.persist(op);
        this.endTransaction();

        final OperationCached op1 = this.dao.findById(6);
        Assert.assertNotNull(op1);
    }

    @Test
    public void update() {

        final String checkSum = "zzzz";

        final OperationCached op = this.dao.findById(1);

        op.setCheckSum(checkSum);

        final OperationCached op1 = this.dao.findById(1);

        Assert.assertNotNull(op1);
        Assert.assertTrue(checkSum.equals(op1.getCheckSum()));
    }

    @Test
    public void deleteByEntity() {

        this.dao.delete(this.dao.findById(3));

        final OperationCached op = this.dao.findById(3);

        Assert.assertNull(op);
    }

    @Test
    public void deleteById() {

        this.dao.delete(2);

        final OperationCached op = this.dao.findById(2);

        Assert.assertNull(op);
    }

    @Test
    public void checkStoredResult() {

        final Integer result = this.dao.checkStoredResult("dddd");

        Assert.assertEquals(40, result.intValue());
    }

    @Test(expected = NoResultException.class)
    public void checkStoredResultException() {

        this.dao.checkStoredResult("mmmm");

        Assert.fail("An exception should be launched.");
    }
}
