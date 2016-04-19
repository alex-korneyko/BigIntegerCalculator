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
 * @version 1.1 18.04.2016
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

    private static final String INT1 = "2";
    private static final String INT2 = "250";
    private static final String INT3 = "32768";

    private static final String BIG1 = "12345678912345678912345678901234567891234567891234567890";
    private static final String BIG2 = "12345678564321658465416876516876136874864136876567891234567890";
    private static final String BIG3 = "2000000000000000";

    private static final ExpressionElement plus = new ExpressionElement(PLUS);
    private static final ExpressionElement minus = new ExpressionElement(MINUS);
    private static final ExpressionElement multiply = new ExpressionElement(MULTIPLY);
    private static final ExpressionElement divide = new ExpressionElement(DIVIDE);
    private static final ExpressionElement power = new ExpressionElement(POWER);


    private static Object[][] parametersSet = new Object[][]{

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
                elementSet.add(plus);
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
            }}, new BigInteger("252")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
                elementSet.add(minus);
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
            }}, new BigInteger("-248")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
                elementSet.add(multiply);
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
            }}, new BigInteger("500")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(BIG1)));
                elementSet.add(plus);
                elementSet.add(new ExpressionElement(new BigInteger(BIG2)));
            }}, new BigInteger("12345690910000570811095788862555038109432028111135782469135780")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(
                        new BigInteger("12345678912345678912345678901234567891234567891234567890")));
                elementSet.add(multiply);
                elementSet.add(new ExpressionElement(new BigInteger(BIG2)));
            }}, new BigInteger("1524157835101439752396474872207764991283432415755461998859314765" +
                    "04109753827778895307058798442637686942262075019052100")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(BIG2)));
                elementSet.add(divide);
                elementSet.add(new ExpressionElement(new BigInteger(BIG3)));
            }}, new BigInteger("6172839282160829232708438258438068437432068438")},

            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(BIG3)));
                elementSet.add(power);
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
            }}, new BigInteger("819200000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000000000000000" +
                    "00000000000000000000000000000000000000000000000000000000000000")},

            //Many operands parameters sets
            //250+32768*2=65786
            {new Expression() {{

                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
                elementSet.add(plus);

                elementSet.add(new ExpressionElement(new BigInteger(INT3)));
                elementSet.add(multiply);

                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
            }}, new BigInteger("65786")},

            //250+32768*2-250^2=3286
            {new Expression() {{
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
                elementSet.add(plus);
                elementSet.add(new ExpressionElement(new BigInteger(INT3)));
                elementSet.add(multiply);
                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
                elementSet.add(minus);
                elementSet.add(new ExpressionElement(new BigInteger(INT2)));
                elementSet.add(power);
                elementSet.add(new ExpressionElement(new BigInteger(INT1)));
            }}, new BigInteger("3286")}
    };
    //endregion
}