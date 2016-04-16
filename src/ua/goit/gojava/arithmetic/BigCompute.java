package ua.goit.gojava.arithmetic;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ... on 12.04.2016.
 */
public class BigCompute implements Observer, Observable {

    List<Observer> observers = new ArrayList<>();
    BigInteger result;
    boolean error = false;

    /**
     * Принимает параметром объект Expression,
     * который содержит коллекцию объектов ExpressionElement.
     * Метод должен определить приоритетные операции, выполнить расчёт всего Expression,
     * и вернуть результат как BigInteger.
     * Смотри диаграмму структуры коллекции выражения: Expression structure v1.0.jpg
     *
     * @param expression
     * @return
     */
    public BigInteger compute(Expression expression) throws ArithmeticException {
        result = new BigInteger("0");
        error = false;

        //Дальше код реализации метода


        //------------------------------

        notifyObservers();
        return result;
    }

    @Override
    public void regObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(error ? "Error" : result.toString());
        }
    }

    @Override
    public void update(Object o) {

        try {
            result = this.compute((Expression) o);
        } catch (ArithmeticException e) {
            error = true;
        }

        notifyObservers();
    }
}
