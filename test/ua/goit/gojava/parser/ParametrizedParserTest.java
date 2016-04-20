package ua.goit.gojava.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;
import ua.goit.gojava.expression.ExpressionElementType;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alex Korneyko, 2016
 * @version 1.0 13.04.2016
 */
@RunWith(value = Parameterized.class)
public class ParametrizedParserTest {

    Parser parser = new Parser();

    String stringExpression;
    Expression expression;

    public ParametrizedParserTest(String stringExpression, Expression expression) {
        this.stringExpression = stringExpression;
        this.expression = expression;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getParametrizedData() {

        return Arrays.asList(parametersSet);
    }

    @Test(timeout = 1000)
    public void testToBigInteger() throws Exception {

        Assert.assertEquals(expression, parser.toBigInteger(stringExpression));

    }

    private static Object[][] parametersSet = new Object[][]{

            //0
            {"123+456",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("123")));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("456")));
                    }}},

            //1
            {"12345678912345678912345678901234567891234567891234567890/456321548765463135446841361",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("12345678912345678912345678901234567891234567891234567890")));
                        elementSet.add(new ExpressionElement(DIVIDE));
                        elementSet.add(new ExpressionElement(new BigInteger("456321548765463135446841361")));
                    }}},

            //2
            {"-456^789",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("-456")));
                        elementSet.add(new ExpressionElement(POWER));
                        elementSet.add(new ExpressionElement(new BigInteger("789")));
                    }}},

            //3
            {"159*(-56)",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("159")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("-56")));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                    }}},

            //4
            {"159+56*456",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("159")));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("56")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(new BigInteger("456")));
                    }}},

            //5
            {"159+56*(-456)",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("159")));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("56")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("-456")));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                    }}},

            //6
            {"159^56*(-456)+225/10",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("159")));
                        elementSet.add(new ExpressionElement(POWER));
                        elementSet.add(new ExpressionElement(new BigInteger("56")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("-456")));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("225")));
                        elementSet.add(new ExpressionElement(DIVIDE));
                        elementSet.add(new ExpressionElement(new BigInteger("10")));
                    }}},

            //7
            {"159^56*(-456+225)/10",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("159")));
                        elementSet.add(new ExpressionElement(POWER));
                        elementSet.add(new ExpressionElement(new BigInteger("56")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("-456")));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("225")));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                        elementSet.add(new ExpressionElement(DIVIDE));
                        elementSet.add(new ExpressionElement(new BigInteger("10")));
                    }}},

            //8
            {"2*(250*(32768+2))/250",
                    new Expression() {{
                        elementSet.add(new ExpressionElement(new BigInteger("2")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("250")));
                        elementSet.add(new ExpressionElement(MULTIPLY));
                        elementSet.add(new ExpressionElement(OPEN_PARENTHESIS));
                        elementSet.add(new ExpressionElement(new BigInteger("32768")));
                        elementSet.add(new ExpressionElement(PLUS));
                        elementSet.add(new ExpressionElement(new BigInteger("2")));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                        elementSet.add(new ExpressionElement(CLOSE_PARENTHESIS));
                        elementSet.add(new ExpressionElement(DIVIDE));
                        elementSet.add(new ExpressionElement(new BigInteger("250")));
                    }}}
    };
}