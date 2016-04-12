package ua.goit.gojava.expression;

import ua.goit.gojava.big.BigInteger;

/**
 *
 * Created by ... on 12.04.2016.
 */
public class ExpressionElement {

    public BigInteger value;

    public ExpressionElementType elementType;

    public ExpressionElement(ExpressionElementType elementType) {
        this.elementType = elementType;
    }

    public ExpressionElement(ExpressionElementType elementType, BigInteger value) {
        this.value = value;
        this.elementType = elementType;
    }

    @Override
    public String toString(){

        return null;
    }

    @Override
    public ExpressionElement clone() {

        return null;
    }

    @Override
    public boolean equals(Object anotherElement) {

        return false;
    }
}
