package ua.goit.gojava.parser;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.arithmetic.BigCompute;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;
import ua.goit.gojava.expression.ExpressionElementType;
import ua.goit.gojava.gui.Screen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.gojava.expression.ExpressionElementType.*;


/**
 * Created by ... on 12.04.2016.
 */
public class Parser implements Observer, Observable {

    private List<Observer> observers = new ArrayList<>();
    Expression expression;
    boolean error;

    /**
     * Метод должен из строки сгенерировать "выражение" (объект класса Expression).
     * Выражение может быть любой длинны, и использовать только следующие
     * математические операции: '+' '-' '*' '/' '^'
     * Должен создать объект Expression, определить все числа и знаки операций, из каждого создать
     * объект ExpressionElement, и поместить в коллекцию этого объекта Expression.
     * Варианты выполнения парсинга описаны в тестовом классе ParametrizedParserTest,
     * должны все тесты пройти на "ОК"
     * В случае получения некорректной строки, должно сгенерироваться исключение IllegalArgumentException.
     * Варианты некорректных строк описаны в тестовом классе ParametrizedParserTest2,
     * все тесты должны пройти на "ОК"
     * <p>
     * Смотри диаграмму структуры коллекции выражения: Expression structure v1.0.jpg
     *
     * @param stringExpression текстовая строка
     * @return объект класса Expression
     */
    public Expression toBigInteger(String stringExpression) throws IllegalArgumentException {

        if (stringExpression == null || stringExpression.length() == 0) {
            throw new IllegalArgumentException();
        }

        expression = new Expression();
        char[] charExpression = stringExpression.toCharArray();
        error = false;

        //Дальше код реализации метода

        fullConvert(stringExpression);

        checkExpression(expression);

        //------------------------------
        notifyObservers();
        return expression;
    }

    private void fullConvert(String strExpression) {
        char[] charExpr = strExpression.toCharArray();
        String stringElement = "";

        for (char aCharExpr : charExpr) {
            if (aCharExpr >= 48 && aCharExpr <= 57) {
                stringElement += aCharExpr;
            }

            if ((aCharExpr >= 33 && aCharExpr <= 47)
                    || (aCharExpr >= 58 && aCharExpr <= 126)) {
                if (!stringElement.isEmpty()) {
                    expression.elementSet.add(new ExpressionElement(new BigInteger(stringElement)));
                    stringElement = "";
                }

                switch (aCharExpr) {
                    case '+':
                        expression.elementSet.add(new ExpressionElement(PLUS));
                        break;
                    case '-':
                        expression.elementSet.add(new ExpressionElement(MINUS));
                        break;
                    case '*':
                        expression.elementSet.add(new ExpressionElement(MULTIPLY));
                        break;
                    case '/':
                        expression.elementSet.add(new ExpressionElement(DIVIDE));
                        break;
                    case '^':
                        expression.elementSet.add(new ExpressionElement(POWER));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation");
                }
            }
        }

        if (!stringElement.isEmpty()) {
            expression.elementSet.add(new ExpressionElement(new BigInteger(stringElement)));
        }
    }

    private void checkExpression(Expression chkExpr) {
        List<ExpressionElement> elementSet = chkExpr.elementSet;

        //Проверка на отрицательность первого числа в выражении
        if (elementSet.get(0).elementType == MINUS &&
                elementSet.get(1).elementType == BIG_INT) {
            elementSet.remove(0);
            elementSet.get(0).value.setSign(BigInteger.Sign.NEGATIVE);
        }

        //Проверка на отрицательность остальных чисел в выражении (кроме первого)
        for (int i = 1; i < elementSet.size(); i++) {
            if (elementSet.get(i).elementType == MINUS &&
                    elementSet.get(i - 1).elementType != BIG_INT &&
                    i < elementSet.size() - 1) {
                elementSet.get(i + 1).value.setSign(BigInteger.Sign.NEGATIVE);
                elementSet.remove(i);
                i--;
            }
        }

        //Проверка на правильное количество элементов в выражении,
        //и заканчивается ли оно сичлами
        if(elementSet.size() < 3 || elementSet.get(0).elementType != BIG_INT ||
                elementSet.get(elementSet.size()-1).elementType != BIG_INT){
            throw new IllegalArgumentException("incorrect number of elements");
        }

        //Проверка, нет ли подряд идущих знаков математических операций
        for (int i=1; i<elementSet.size(); i++){
            if(elementSet.get(i).elementType != BIG_INT &&
                    elementSet.get(i-1).elementType != BIG_INT){
                throw new IllegalArgumentException("Wrong operations");
            }
        }
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
            if (error) {
                if (observer instanceof Screen) {
                    observer.update("Error!");
                }
            } else {
                if (observer instanceof BigCompute) {
                    observer.update(expression);
                }
            }
        }
    }

    @Override
    public void update(Object o) {

        try {
            expression = this.toBigInteger((String) o);
        } catch (IllegalArgumentException e) {
            error = true;
        }

        notifyObservers();
    }
}
