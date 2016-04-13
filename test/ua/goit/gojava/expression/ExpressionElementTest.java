package ua.goit.gojava.expression;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.gojava.big.BigInteger;

import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.0 13.04.2016
 */
public class ExpressionElementTest {

    private ExpressionElement shortBigInteger = new ExpressionElement(new BigInteger("123"));
    private ExpressionElement longBigInteger =
            new ExpressionElement(new BigInteger("12345678912345678912345678901234567891234567891234567890"));
    private ExpressionElement operationPlus = new ExpressionElement(PLUS);

    @Test(timeout = 1000)
    public void testToString() throws Exception {
        final String shortBigIntegerString = shortBigInteger.toString();
        final String longBigIntegerString = longBigInteger.toString();
        final String operationPlusString = operationPlus.toString();

        Assert.assertEquals("123", shortBigIntegerString);
        Assert.assertEquals("12345678912345678912345678901234567891234567891234567890", longBigIntegerString);
        Assert.assertEquals("+", operationPlusString);
    }

    @Test(timeout = 1000)
    public void testClone() throws Exception {
        final ExpressionElement anotherShortBigInteger = shortBigInteger.clone();
        final ExpressionElement anotherLongBigInteger = longBigInteger.clone();
        final ExpressionElement anotherOperationPlus = operationPlus.clone();

        Assert.assertEquals(new ExpressionElement(new BigInteger("123")), anotherShortBigInteger);
        Assert.assertEquals(false, shortBigInteger == anotherShortBigInteger);

        Assert.assertEquals(new ExpressionElement(
                new BigInteger("12345678912345678912345678901234567891234567891234567890")), anotherLongBigInteger);
        Assert.assertEquals(false, longBigInteger == anotherLongBigInteger);

        Assert.assertEquals(new ExpressionElement(PLUS), anotherOperationPlus);
        Assert.assertEquals(false, operationPlus == anotherOperationPlus);

    }

    @Test(timeout = 1000)
    public void testEquals() throws Exception {

        Assert.assertEquals(true, shortBigInteger.equals(new ExpressionElement(new BigInteger("123"))));
        Assert.assertEquals(false, shortBigInteger.equals(new ExpressionElement(new BigInteger("123456"))));
        Assert.assertEquals(true, operationPlus.equals(new ExpressionElement(PLUS)));
    }

    @Test(timeout = 1000)
    public void testCompareTo() throws Exception {

        int compareResult;

        compareResult = shortBigInteger.compareTo(new ExpressionElement(new BigInteger("100")));
        Assert.assertEquals(true, compareResult > 0);

        compareResult = shortBigInteger.compareTo(new ExpressionElement(new BigInteger("150")));
        Assert.assertEquals(true, compareResult < 0);

        compareResult = shortBigInteger.compareTo(new ExpressionElement(new BigInteger("123")));
        Assert.assertEquals(true, compareResult == 0);

        compareResult = operationPlus.compareTo(new ExpressionElement(MINUS));
        Assert.assertEquals(0, compareResult);

        compareResult = operationPlus.compareTo(new ExpressionElement(MULTIPLY));
        Assert.assertEquals(true, compareResult < 0);

        compareResult = new ExpressionElement(MULTIPLY).compareTo(new ExpressionElement(MINUS));
        Assert.assertEquals(true, compareResult > 0);
    }
}