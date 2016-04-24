package ua.goit.gojava.big;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * Created by admin on 12.04.2016.
 */
public class BigDecimal extends BigNumber {

    private BigInteger intPart;
    private BigInteger fractionPart;
    private int precision = 30;

    BigDecimal() {
    }

    BigDecimal(String stringValue) {
        this.intPart = parseBigDecimal(stringValue).getIntegerPart();
        this.fractionPart = parseBigDecimal(stringValue).getFractionPart();
    }

    BigDecimal(BigInteger bigIntegerValue) {
        this.intPart = bigIntegerValue;
        this.fractionPart = new BigInteger(0);
    }

    BigDecimal(int intValue) {
        this.intPart = new BigInteger(intValue);
        this.fractionPart = new BigInteger(0);
    }


    BigDecimal(Double doubleValue) {
        intPart = new BigInteger(doubleValue.intValue());
        double tempDoubleVal = doubleValue - doubleValue.intValue();
        StringBuilder stringDoubleValue = new StringBuilder("");

        for (int i = 0; i < precision; i++) {
            stringDoubleValue.append((int) (tempDoubleVal *= 10));
            tempDoubleVal = tempDoubleVal - (int) tempDoubleVal;
        }

        fractionPart = new BigInteger(stringDoubleValue.toString());
    }

    public BigInteger getIntegerPart() {
        return intPart;
    }

    public BigInteger getFractionPart() {
        return fractionPart;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Contract("null -> fail")
    public static BigDecimal parseBigDecimal(String stringValue) throws NumberFormatException {

        if (stringValue == null || Objects.equals(stringValue, "")) {
            throw new NumberFormatException();
        }

        BigDecimal result = new BigDecimal();

        StringBuilder stringBuilder = new StringBuilder(stringValue);

        //Поиск десятичной точки
        int dotIndex = stringValue.indexOf('.');

        //Если точки нет
        if (dotIndex == -1) {

            result.intPart = BigInteger.parseBigInteger(stringValue);
            result.fractionPart = new BigInteger(0);

            return result;
        }

        //Если в строке больше одной точки, то исключение
        if (dotIndex != stringValue.lastIndexOf('.')) {
            throw new NumberFormatException();
        }

        String intPartString = stringBuilder.substring(0, dotIndex - 1);
        String fractionPartString = stringBuilder.substring(dotIndex + 1, stringBuilder.length() - 1);

        for (char cdigit : intPartString.toCharArray()) {
            if (cdigit < '0' || cdigit > '9') {
                throw new NumberFormatException();
            }
        }

        for (char cdigit : fractionPartString.toCharArray()) {
            if (cdigit < '0' || cdigit > '9') {
                throw new NumberFormatException();
            }
        }

        if (intPartString.equals("")) {
            result.intPart = new BigInteger(0);
        } else {
            result.intPart = BigInteger.parseBigInteger(intPartString);
        }

        if (fractionPartString.equals("")) {
            result.fractionPart = new BigInteger(0);
        } else {
            result.fractionPart = BigInteger.parseBigInteger(fractionPartString);
        }

        return result;
    }

    @Override
    public String toString() {
        return intPart.toString() + "." + fractionPart.toString();
    }


    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
