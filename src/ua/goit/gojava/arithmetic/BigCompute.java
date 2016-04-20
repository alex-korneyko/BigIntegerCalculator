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
 * @author Ihor Pylyavets, 2016
 * @version 2.0 20.04.2016
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
     * Метод поочереди проходит коллекцию и ищет обьекты с нужными приоритетами (от 3 до 1)
     * когда находит нужный приоритет делает операцию и удаляет два бьекта, результат записывается в первый
     * a+b  --- результат запишется в *a*, *+b* удалится
     * @param expression
     * @return
     */
    // Expression expression:
    // public List<ExpressionElement> elementSet = new ArrayList<>();
    public BigInteger compute(Expression expression) throws ArithmeticException {
        result = new BigInteger("0");
        error = false;

        if (!isCorrectExpression(expression)) {
            throw new ArithmeticException("Your Expression is Incorrect");
        }

        while (isParenthesis(expression)) {
            doComputeInBrackets(expression);
        }

        result = expressDecision(expression.elementSet);

        notifyObservers();

        return result;
    }

    private boolean isCorrectExpression(Expression expression) {
        int countOpenParenthesis = 0;
        int countCloseParenthesis = 0;

        for (ExpressionElement expressionElement : expression.elementSet) {
            if (expressionElement.elementType == ExpressionElementType.OPEN_PARENTHESIS) {
                countOpenParenthesis += 1;
            }

            if (expressionElement.elementType == ExpressionElementType.CLOSE_PARENTHESIS) {
                countCloseParenthesis += 1;
            }
        }

        if (countOpenParenthesis != countCloseParenthesis) {
            return false;
        }

        return true;
    }

    private boolean isParenthesis(Expression expression) {
        for (ExpressionElement expressionElement : expression.elementSet) {
            if (expressionElement.elementType == ExpressionElementType.OPEN_PARENTHESIS)
                return true;
        }
        return false;
    }

    private void doComputeInBrackets(Expression expression) {
        int[] arrayFromTo = searchExpressionToCompute(expression.elementSet);

        List<ExpressionElement> elementSetForCompute =
                new ArrayList<ExpressionElement>(expression.elementSet.subList(arrayFromTo[0] + 1, arrayFromTo[1]));

        expressDecision(elementSetForCompute);

        removeElementFromList(expression.elementSet, arrayFromTo[0], arrayFromTo[1]);

        expression.elementSet.add(arrayFromTo[0], elementSetForCompute.get(0));
    }

    private int[] searchExpressionToCompute(List<ExpressionElement> elementSet) {
        int[] result = {0, 0};
        boolean flag = false;

        for (int i = 0; i < elementSet.size(); i++) {
            if (elementSet.get(i).elementType == ExpressionElementType.OPEN_PARENTHESIS) {
                flag = true;
                result[0] = i;
            }

            if (elementSet.get(i).elementType == ExpressionElementType.CLOSE_PARENTHESIS && flag) {
                flag = false;
                result[1] = i;
            }

            if (elementSet.get(i).elementType != ExpressionElementType.OPEN_PARENTHESIS && flag
                    && elementSet.get(i).elementType != ExpressionElementType.CLOSE_PARENTHESIS) {
            }

            if (elementSet.get(i).elementType == ExpressionElementType.OPEN_PARENTHESIS) {
                result[0] = i;
            }
        }

        return result;
    }

    private BigInteger expressDecision(List<ExpressionElement> elementSet) {
        while (isOperationPriority(elementSet, 3)) {
            doOperation(elementSet, 3);
        }

        while (isOperationPriority(elementSet, 2)) {
            doOperation(elementSet, 2);
        }

        while (isOperationPriority(elementSet, 1)) {
            doOperation(elementSet, 1);
        }

        return elementSet.get(0).value;
    }

    private void removeElementFromList(List<ExpressionElement> list, int from, int to) {
        for (int i = from - 1, j = from; i < to; i++) {
            list.remove(j);
        }
    }

    private boolean isOperationPriority(List<ExpressionElement> elementSet, int operationPriority) {
        for (ExpressionElement expressionElement : elementSet) {
            if (expressionElement.operationPriority == operationPriority)
                return true;
        }
        return false;
    }

    private List<ExpressionElement> doOperation(List<ExpressionElement> elementSet, int operationPriority) {
        for (int i = 1; i < elementSet.size()-1; i++) {
            if (elementSet.get(i).operationPriority == operationPriority) {
                if (operationPriority == 3) {
                    elementSet.get(i-1).value =
                            BigMath.power(elementSet.get(i-1).value, elementSet.get(i+1).value);
                }

                else if (operationPriority == 2) {
                    if (elementSet.get(i).elementType == ExpressionElementType.MULTIPLY) {
                        elementSet.get(i-1).value =
                                BigMath.multiply(elementSet.get(i-1).value, elementSet.get(i+1).value);
                    }
                    else if (elementSet.get(i).elementType == ExpressionElementType.DIVIDE){
                        elementSet.get(i-1).value =
                                BigMath.divide(elementSet.get(i-1).value, elementSet.get(i+1).value);
                    }
                }

                else if (operationPriority == 1) {
                    if (elementSet.get(i).elementType == ExpressionElementType.PLUS) {
                        elementSet.get(i-1).value =
                                BigMath.add(elementSet.get(i-1).value, elementSet.get(i+1).value);
                    }
                    else if (elementSet.get(i).elementType == ExpressionElementType.MINUS){
                        elementSet.get(i-1).value =
                                BigMath.sub(elementSet.get(i-1).value, elementSet.get(i+1).value);
                    }
                }

                elementSet.remove(i+1);
                elementSet.remove(i);

                return elementSet;
            }
        }

        return elementSet;
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
}
