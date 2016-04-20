//package ua.goit.gojava.parser;

//import ua.goit.gojava.Observable;
//import ua.goit.gojava.Observer;
//import ua.goit.gojava.arithmetic.BigCompute;
//import ua.goit.gojava.big.BigInteger;
//import ua.goit.gojava.expression.Expression;
//import ua.goit.gojava.expression.ExpressionElement;
//import ua.goit.gojava.expression.ExpressionElementType;
//import ua.goit.gojava.gui.Screen;

import javax.swing.*;
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
        String number;
        int sign=0;
        int i=0;

        if ((stringExpression.length()==1)             //проверка на то что введенный нами символ не знак - это ошибка!
                && ((stringExpression.charAt(0)=='+')
                || (stringExpression.charAt(0)=='-')
                || (stringExpression.charAt(0)=='*')
                || (stringExpression.charAt(0)=='/')
                || (stringExpression.charAt(0)=='^')))
        {   error=true;
            notifyObservers();
            return expression;
        }

        while (i<stringExpression.length())         //прохождение всех позиций в нашей строке
        {
            number="";
            String c=""+stringExpression.charAt(i);
            while (!(c.equals(ExpressionElementType.PLUS.toString())) &&    //данный цикл обьеденяет символы в единое число до встречи с знаком
                    !(c.equals(ExpressionElementType.MINUS.toString())) &&
                    !(c.equals(ExpressionElementType.MULTIPLY.toString())) &&
                    !(c.equals(ExpressionElementType.DIVIDE.toString())) &&
                    !(c.equals(ExpressionElementType.POWER.toString()))) {
                number += c;                            //накапливаем числа
                if (i < stringExpression.length() - 1) i++;
                else break;
                c = "" + stringExpression.charAt(i);
            }

            if (number.isEmpty()) {    //если пустой, значит там знак
                if (i==stringExpression.length() - 1) {error = true; break;} //знак в последней позиции - ошибка
                else if (i==0) {
                    if (c.equals("+")) sign=0; //если в начале числа "+" то все ок,
                    if (c.equals("-")) sign=1; //если "-" то отмечаем sign=1
                    if ((c.equals("*")) || (c.equals("/")) || (c.equals("^"))) {error=true; break;} //иначе ошибка
                    i++;
                }
                else {
                    switch(c) {  //ловим знак операции и помещаем в Expression
                        case "+":
                            expression.elementSet.add(new ExpressionElement(ExpressionElementType.PLUS)); break;
                        case "-":
                            expression.elementSet.add(new ExpressionElement(ExpressionElementType.MINUS)); break;
                        case "*":
                            expression.elementSet.add(new ExpressionElement(ExpressionElementType.MULTIPLY)); break;
                        case "/":
                            expression.elementSet.add(new ExpressionElement(ExpressionElementType.DIVIDE)); break;
                        case "^":
                            expression.elementSet.add(new ExpressionElement(ExpressionElementType.POWER)); break;
                    }
                    switch(stringExpression.charAt(i-1)) { //Ловим ситуацию когда два знака подряд - это ошибка
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                        case '^':
                            error=true;
                    }
                    i++;
                }
            } else {  //обрабатываем числа
                if (sign==1) number="-"+number; //если знак 1 - значит число отрецательное
                sign=0;
                expression.elementSet.add(new ExpressionElement(new BigInteger(number)));

                if ((i == stringExpression.length() - 1)//проверяем на то что последняя позиция - знак - ошибка
                        && ((stringExpression.charAt(i)=='+')
                        || (stringExpression.charAt(i)=='-')
                        || (stringExpression.charAt(i)=='*')
                        || (stringExpression.charAt(i)=='/')
                        || (stringExpression.charAt(i)=='^'))) {error=true; break;}
                if (i == stringExpression.length() - 1) break;//i++;
            }
        }

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