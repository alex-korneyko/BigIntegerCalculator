package ua.goit.gojava.big;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.0 17.04.2016
 */
@RunWith(value = Parameterized.class)
public class ParametrizedBigMathTest {

    BigInteger big1;
    BigInteger big2;

    BigInteger plusResult;
    BigInteger minusResult;
    BigInteger multiplyResult;
    BigInteger divideResult;
    BigInteger powerResult;
    BigInteger moduloResult;

    public ParametrizedBigMathTest(BigInteger big1, BigInteger big2, BigInteger plusResult, BigInteger minusResult,
                                   BigInteger multiplyResult, BigInteger divideResult, BigInteger powerResult,
                                   BigInteger moduloResult) {

        this.big1 = big1;
        this.big2 = big2;
        this.plusResult = plusResult;
        this.minusResult = minusResult;
        this.multiplyResult = multiplyResult;
        this.divideResult = divideResult;
        this.powerResult = powerResult;
        this.moduloResult = moduloResult;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getParametrizedData() {
        return Arrays.asList(parametersSet);
    }

    @Test(timeout = 1000)
    public void testPlus() throws Exception {
        Assert.assertEquals(plusResult, BigMath.add(big1, big2));
    }

    @Test(timeout = 1000)
    public void testMinus() throws Exception {
        Assert.assertEquals(minusResult, BigMath.sub(big1, big2));
    }

    @Test(timeout = 1000)
    public void testMultiply() throws Exception {
        Assert.assertEquals(multiplyResult, BigMath.multiply(big1, big2));
    }

    @Test(timeout = 1000)
    public void testDivide() throws Exception {
        Assert.assertEquals(divideResult, BigMath.divide(big1, big2));
    }

    @Test(timeout = 1000)
    public void testPower() throws Exception {
        Assert.assertEquals(powerResult, BigMath.power(big1, big2));
    }

    @Test(timeout = 1000)
    public void testModulo() throws Exception {
        //Assert.assertEquals(moduloResult, BigMath.modulo(big1, big2));
    }

    //Parameters sets
    private static Object[][] parametersSet = new Object[][]{
            //Parameters set 1
            {new BigInteger("63"), new BigInteger("3"), new BigInteger("66"), new BigInteger("60"),
                    new BigInteger("189"), new BigInteger("21"), new BigInteger("250047"), new BigInteger("0")},

            //Parameters set 2
            {new BigInteger("12345678912345678912345678901234567891234567891234567890"),        /*arg1*/
                    new BigInteger("3"),                                                        /*arg2*/
                    new BigInteger("12345678912345678912345678901234567891234567891234567893"), /*plus*/
                    new BigInteger("12345678912345678912345678901234567891234567891234567887"), /*minus*/
                    new BigInteger("37037036737037036737037036703703703673703703673703703670"), /*multiply*/
                    new BigInteger("4115226304115226304115226300411522630411522630411522630"),  /*divide*/
                    new BigInteger("188167637743418398755459182716237369242681145892581883" +
                            "0667366479562354259321539139589284286097439430" +
                            "1364585303055445194136074120895715112826315549" +
                            "54480361860897069000"),                                            /*power*/
                    new BigInteger("0")                                                         /*mod*/
            }
    };
}