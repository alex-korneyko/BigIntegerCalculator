package ua.goit.gojava.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;
import ua.goit.gojava.expression.ExpressionElementType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ua.goit.gojava.expression.ExpressionElementType.*;

/**
 * @author Alex Korneyko, 2016
 * @version 1.0 13.04.2016
 */
@RunWith(value = Parameterized.class)
public class ParametrizedParserTest2 {

    String stringExpression;

    Parser parser = new Parser();

    public ParametrizedParserTest2(String stringExpression) {
        this.stringExpression = stringExpression;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getParametrizedData() {

        return Arrays.asList(parametersSet);
    }

    @Test(expected = IllegalArgumentException.class, timeout = 1000)
    public void testToBigInteger() throws Exception {

        Assert.assertEquals(null, parser.toBigInteger(stringExpression));
    }

    private static Object[][] parametersSet = new Object[][]{
            // 0         1        2        3      4      5       6            7
            {"789"}, {"-123"}, {"456+"}, {"+"}, {"^"}, {"+*"}, {"45+32-"}, {"654-56^"},
            // 8           9           10            11           12           13
            {"48+*45"}, {"45+*/123"}, {"9*^2"}, {"45+((3*7-6)"}, {"56=9"}, {"23+)7-9*6("}
    };
}