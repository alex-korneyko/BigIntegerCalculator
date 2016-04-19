package ua.goit.gojava.big;

/**
 *
 * Created by ... on 12.04.2016.
 */
public class BigMath {
    public static BigInteger add(BigInteger x, BigInteger y){
        return x.add(y);
    }

    public static BigInteger sub(BigInteger x, BigInteger y){
        return x.subtract(y);
    }

    public static BigInteger multiply(BigInteger x, BigInteger y){
        return x.multiply(y);
    }

    public static BigInteger divide(BigInteger x, BigInteger y){
        return x.divide(y);
    }

    public static BigInteger power(BigInteger x, BigInteger y){
        return x.pow(y);
    }

    public static BigInteger modulo(BigInteger x, BigInteger y){
        return x.mod(y);
    }
}
