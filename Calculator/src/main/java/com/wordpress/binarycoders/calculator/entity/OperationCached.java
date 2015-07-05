package com.wordpress.binarycoders.calculator.entity;

import com.wordpress.binarycoders.calculator.entity.utils.Sequence;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author fjavierm
 */
@Entity
@Table(name = "operationCached")
@NamedQueries({
    @NamedQuery(name = OperationCached.CHECK_STORED_RESULT, query = "SELECT oc.operationResult FROM OperationCached oc where oc.checkSum = :checkSum")
})
@TableGenerator(name = Sequence.GENERATOR, table = Sequence.TABLE,
        pkColumnName = Sequence.ID_COLUMN_NAME, valueColumnName = Sequence.LAST_VALUE_COLUMN_NAME,
        pkColumnValue = "idopcached", allocationSize = 1
)
public class OperationCached implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CHECK_STORED_RESULT = "OperationCached.checkStoredResult";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = Sequence.GENERATOR)
    @Column(name = "id")
    private Integer id;

    @Column(name = "checkSum", nullable = false)
    private String checkSum;

    @Column(name = "operationResult", nullable = false)
    private Integer operationResult;

    public Integer getId() {

        return this.id;
    }

    public void setId(final Integer id) {

        this.id = id;
    }

    public String getCheckSum() {

        return this.checkSum;
    }

    public void setCheckSum(final String checkSum) {

        this.checkSum = checkSum;
    }

    public Integer getOperationResult() {

        return this.operationResult;
    }

    public void setOperationResult(final Integer operationResult) {

        this.operationResult = operationResult;
    }

}
