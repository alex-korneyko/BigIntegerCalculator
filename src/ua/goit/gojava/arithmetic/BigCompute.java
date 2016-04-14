package ua.goit.gojava.arithmetic;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;

/**
 *
 * Created by ... on 12.04.2016.
 */
public class BigCompute implements Observer, Observable {

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
    public BigInteger compute(Expression expression) {

        return null;
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
