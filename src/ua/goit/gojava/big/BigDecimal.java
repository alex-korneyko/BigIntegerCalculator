package ua.goit.gojava.big;

import java.util.Objects;

/**
 * Created by admin on 12.04.2016.
 */
public class BigDecimal extends BigNumber {

    private BigInteger intPart;
    private BigInteger fractionPart;

    BigDecimal() {
    }

    BigDecimal(String stringValue){
    }

    public BigInteger getIntegerPart(){
        return intPart;
    }

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
