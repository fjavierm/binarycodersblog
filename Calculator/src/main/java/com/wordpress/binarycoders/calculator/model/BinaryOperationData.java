package com.wordpress.binarycoders.calculator.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fjavierm
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BinaryOperationData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer firstOperand;
    private Integer secondOperand;

    public Integer getFirstOperand() {

        return this.firstOperand;
    }

    public void setFirstOperand(final Integer firstOperand) {

        this.firstOperand = firstOperand;
    }

    public Integer getSecondOperand() {

        return this.secondOperand;
    }

    public void setSecondOperand(final Integer secondOperand) {

        this.secondOperand = secondOperand;
    }

}
