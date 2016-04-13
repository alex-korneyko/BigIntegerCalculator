package ua.goit.gojava.parser;

import ua.goit.gojava.Observable;
import ua.goit.gojava.Observer;
import ua.goit.gojava.expression.Expression;

import java.util.List;


/**
 *
 * Created by ... on 12.04.2016.
 */
public class Parser implements Observer, Observable {

    private List<Observer> observers;

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
     *
     *
     * @param stringExpression текстовая строка
     * @return объект класса Expression
     */
    public Expression toBigInteger(String stringExpression) {

        Expression expression = new Expression();

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

    }

    @Override
    public void update(Object o) {

    }
}
