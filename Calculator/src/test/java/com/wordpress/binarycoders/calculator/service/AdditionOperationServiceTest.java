package com.wordpress.binarycoders.calculator.service;

import com.wordpress.binarycoders.calculator.dao.OperationCachedDao;
import com.wordpress.binarycoders.calculator.model.BinaryOperationData;
import com.wordpress.binarycoders.calculator.utils.CheckSum;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author fjavierm
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CheckSum.class)
public class AdditionOperationServiceTest {

    @Mock
    private OperationCachedDao dao;

    @InjectMocks
    private AdditionOperationService service;

    private BinaryOperationData data1;
    private BinaryOperationData data2;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {

        this.data1 = new BinaryOperationData();
        this.data1.setFirstOperand(1);
        this.data1.setSecondOperand(2);

        this.data2 = new BinaryOperationData();
        this.data2.setFirstOperand(3);
        this.data2.setSecondOperand(4);

        PowerMockito.mockStatic(CheckSum.class);

        Mockito.when(this.dao.checkStoredResult("aaaa")).thenReturn(3);
        Mockito.when(this.dao.checkStoredResult("zzzz")).thenThrow(NoResultException.class);

        PowerMockito.when(CheckSum.calculate(data1, Operator.ADDITION)).thenReturn("aaaa");
        PowerMockito.when(CheckSum.calculate(data2, Operator.ADDITION)).thenReturn("zzzz");
    }

    @Test
    public void perform() {

        final Integer result = this.service.perform(this.data1);

        Assert.assertEquals(3, result.intValue());
    }

    @Test
    public void performMultiple() {

        final List<BinaryOperationData> data = new ArrayList<>();
        data.add(this.data1);
        data.add(this.data2);

        final List<Integer> result = this.service.performMultiple(data);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.get(0).intValue());
        Assert.assertEquals(7, result.get(1).intValue());
    }

    @Test
    public void performMultipleHeavy() {

        final List<BinaryOperationData> data = new ArrayList<>();
        data.add(this.data1);
        data.add(this.data2);

        final List<Integer> result = this.service.performMultipleHeavy(data);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.get(0).intValue());
        Assert.assertEquals(7, result.get(1).intValue());
    }
}
