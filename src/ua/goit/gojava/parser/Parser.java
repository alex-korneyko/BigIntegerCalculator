package ua.goit.gojava.parser;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.arithmetic.BigCompute;
import ua.goit.gojava.big.BigInteger;
import ua.goit.gojava.expression.Expression;
import ua.goit.gojava.expression.ExpressionElement;
import ua.goit.gojava.expression.ExpressionElementType;
import ua.goit.gojava.gui.Screen;

import java.util.ArrayList;
import java.util.List;


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

        expression = new Expression();
        error = false;

        //Дальше код реализации метода



        //------------------------------
        notifyObservers();
        return expression;
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
