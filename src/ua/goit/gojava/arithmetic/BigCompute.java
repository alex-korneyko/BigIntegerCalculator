package ua.goit.gojava.arithmetic;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.big.BigMath;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;
import ua.goit.gojava.expression.ExpressionElementType;

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
    // Expression expression:
    // public List<ExpressionElement> elementSet = new ArrayList<>();
    public BigInteger compute(Expression expression) throws ArithmeticException {
        //result = new BigInteger("0");
        error = false;

        while (isOperationPriority(expression, 3)) {
            doOperation(expression, 3);
        }

        while (isOperationPriority(expression, 2)) {
            doOperation(expression, 2);
        }

        while (isOperationPriority(expression, 1)) {
            doOperation(expression, 1);
        }

        notifyObservers();

        return expression.elementSet.get(0).value;
        //return (new BigInteger("250")).add(new BigInteger("2"));
        //return result;
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
            observer.update(error ? "Error!" : result.toString());
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

    private boolean isOperationPriority(Expression expression, int operationPriority) {
        for (ExpressionElement expressionElement : expression.elementSet) {
            if (expressionElement.operationPriority == operationPriority)
                return true;
        }
        return false;
    }

    private Expression doOperation(Expression expression, int operationPriority) {
        for (int i = 1; i < expression.elementSet.size()-1; i++) {
            if (expression.elementSet.get(i).operationPriority == operationPriority) {
                if (operationPriority == 3) {
                    expression.elementSet.get(i-1).value =
                            BigMath.power(expression.elementSet.get(i-1).value, expression.elementSet.get(i+1).value);
                }

                else if (operationPriority == 2) {
                    if (expression.elementSet.get(i).elementType == ExpressionElementType.MULTIPLY) {
                        expression.elementSet.get(i-1).value =
                                BigMath.multiply(expression.elementSet.get(i-1).value, expression.elementSet.get(i+1).value);
                    }
                    else if (expression.elementSet.get(i).elementType == ExpressionElementType.DIVIDE){
                        expression.elementSet.get(i-1).value =
                                BigMath.divide(expression.elementSet.get(i-1).value, expression.elementSet.get(i+1).value);
                    }
                }

                else if (operationPriority == 1) {
                    if (expression.elementSet.get(i).elementType == ExpressionElementType.PLUS) {
                        expression.elementSet.get(i-1).value =
                                BigMath.add(expression.elementSet.get(i-1).value, expression.elementSet.get(i+1).value);
                    }
                    else if (expression.elementSet.get(i).elementType == ExpressionElementType.MINUS){
                        expression.elementSet.get(i-1).value =
                                BigMath.sub(expression.elementSet.get(i-1).value, expression.elementSet.get(i+1).value);
                    }
                }

                expression.elementSet.remove(i);
                expression.elementSet.remove(i);

                return expression;
            }
        }

        return expression;
    }
}
