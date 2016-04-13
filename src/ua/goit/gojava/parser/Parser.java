package ua.goit.gojava.parser;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;

/**
 *
 * Created by ... on 12.04.2016.
 */
public class Parser implements Observer, Observable {

    public static Expression toBigInteger(String stringExpression){



        return new Expression();
    }


    @Override
    public void regObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void update(Object o) {

    }
}
