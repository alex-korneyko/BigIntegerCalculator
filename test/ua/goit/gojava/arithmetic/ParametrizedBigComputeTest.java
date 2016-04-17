package ua.goit.gojava.arithmetic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.0 16.04.2016
 */
@RunWith(value = Parameterized.class)
public class ParametrizedBigComputeTest {

    BigCompute bigCompute = new BigCompute();

    Expression expression;
    BigInteger result;

    public ParametrizedBigComputeTest(Expression expression, BigInteger result) {
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getParametrizedData() {

        return Arrays.asList(parametersSet);
    }

    @Test(timeout = 1000)
    public void testCompute() throws Exception {

        Assert.assertEquals(result, bigCompute.compute(expression));
    }

    //region parametersSet
    private static ExpressionElement int1 = new ExpressionElement(new BigInteger("2"));
    private static ExpressionElement int2 = new ExpressionElement(new BigInteger("250"));
    private static ExpressionElement int3 = new ExpressionElement(new BigInteger("32768"));

    private static ExpressionElement big1 = new ExpressionElement(
            new BigInteger("12345678912345678912345678901234567891234567891234567890"));
    private static ExpressionElement big2 = new ExpressionElement(
            new BigInteger("12345678564321658465416876516876136874864136876567891234567890"));
    private static ExpressionElement big3 = new ExpressionElement(new BigInteger("2000000000000000"));

    private static ExpressionElement plus = new ExpressionElement(PLUS);
    private static ExpressionElement minus = new ExpressionElement(MINUS);
    private static ExpressionElement multiply = new ExpressionElement(MULTIPLY);
    private static ExpressionElement divide = new ExpressionElement(DIVIDE);
    private static ExpressionElement power = new ExpressionElement(POWER);


    private static Object[][] parametersSet = new Object[][]{

            {new Expression() {{
                elementSet.add(int1);
                elementSet.add(plus);
                elementSet.add(int2);
            }}, new BigInteger("252")},

            {new Expression() {{
                elementSet.add(int1);
                elementSet.add(multiply);
                elementSet.add(int2);
            }}, new BigInteger("500")},

            {new Expression() {{
                elementSet.add(big1);
                elementSet.add(plus);
                elementSet.add(big2);
            }}, new BigInteger("12345690910000570811095788862555038109432028111135782469135780")},

            {new Expression() {{
                elementSet.add(big1);
                elementSet.add(multiply);
                elementSet.add(big2);
            }}, new BigInteger("152415783510143975239647487220776499128343241575546199885931476504109753827778895307058798442637686942262075019052100")},

            {new Expression() {{
                elementSet.add(big2);
                elementSet.add(divide);
                elementSet.add(big3);
            }}, new BigInteger("6172839282160829232708438258438068437432068438")},

            {new Expression() {{
                elementSet.add(big3);
                elementSet.add(power);
                elementSet.add(int2);
            }}, new BigInteger("819200000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000")},

            //Many operands parameters sets
            //250+32768*2
            {new Expression() {{
                elementSet.add(int2);
                elementSet.add(plus);
                elementSet.add(int3);
                elementSet.add(multiply);
                elementSet.add(int1);
            }}, new BigInteger("65786")},

            //250+32768*2-250^2
            {new Expression() {{
                elementSet.add(int2);
                elementSet.add(plus);
                elementSet.add(int3);
                elementSet.add(multiply);
                elementSet.add(int1);
                elementSet.add(minus);
                elementSet.add(int2);
                elementSet.add(power);
                elementSet.add(int1);
            }}, new BigInteger("3286")}
    };
    //endregion
}