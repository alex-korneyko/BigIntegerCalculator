package ua.goit.gojava.expression;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.goit.gojava.big.BigInteger;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.0 13.04.2016
 */
@RunWith(value = Parameterized.class)
public class ParametrizedExpressionTest {

    private Expression expression;
    private Expression anotherExpression;
    private String expressionElementString;
    private boolean equal;


    public ParametrizedExpressionTest(Expression expression, Expression anotherExpression, String expressionElementString, boolean equal) {
        this.expression = expression;
        this.anotherExpression = anotherExpression;
        this.expressionElementString = expressionElementString;
        this.equal = equal;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getParametrizedDataForToString() {
        return Arrays.asList(parameters);
    }


    @Test(timeout = 1000)
    public void testToString() throws Exception {
        Assert.assertEquals(expressionElementString, expression.toString());
    }

    @Test(timeout = 1000)
    public void testClone() throws Exception {
        Expression clonedExpression = expression.clone();
        Assert.assertEquals(true,expression.equals(clonedExpression));
        Assert.assertEquals(false, expression == clonedExpression);
    }

    @Test(timeout = 1000)
    public void testEquals() throws Exception {
        Assert.assertEquals(equal, expression.equals(anotherExpression));
    }

    private static Object[][] parameters = new Object[][]{
            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger("123")));
                elementSet.add(new ExpressionElement(MULTIPLY));
                elementSet.add(new ExpressionElement(
                        new BigInteger("12345678912345678912345678901234567891234567891234567890")));
            }}, new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger("123")));
                elementSet.add(new ExpressionElement(MULTIPLY));
                elementSet.add(new ExpressionElement(
                        new BigInteger("12345678912345678912345678901234567891234567891234567890")));
            }},
                    "123*12345678912345678912345678901234567891234567891234567890",
                    true},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger("123")));
                elementSet.add(new ExpressionElement(POWER));
                elementSet.add(new ExpressionElement(
                        new BigInteger("12345678912345678912345678901234567891234567891234567890")));
            }}, new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger("123")));
                elementSet.add(new ExpressionElement(MULTIPLY));
                elementSet.add(new ExpressionElement(
                        new BigInteger("123")));
            }},
                    "123^12345678912345678912345678901234567891234567891234567890",
                    false}
    };

}