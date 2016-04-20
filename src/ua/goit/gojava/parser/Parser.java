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
 * @author Ihor Pylyavets, 2016
 * @version 1.0 19.04.2016
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

        char[] charArray = stringExpression.toCharArray();

        validationExpression(charArray);

        String str = "";
        for (int i = 0; i < charArray.length; i++) {
            if (isSign(charArray[i])) {
                if (str.length() == 0 && isSignMinus(charArray[i])) {
                    str = String.valueOf(charArray[i]);
                } else {
                    if (str.length() != 0) {
                        expression.elementSet.add(new ExpressionElement(new BigInteger(str)));
                    }
                    String stringSign = String.valueOf(charArray[i]);
                    expression.elementSet.add(new ExpressionElement(ExpressionElementType.stringToType(stringSign)));

                    str = "";
                }
            } else if (isDigit(charArray[i])) {
                if (str.length() == 0) {
                    str = String.valueOf(charArray[i]);
                } else {
                    str = str.concat(String.valueOf(charArray[i]));
                }
            }
        }

        if (!str.isEmpty()) {
            expression.elementSet.add(new ExpressionElement(new BigInteger(str)));
        }

        if (expression.elementSet.size() <= 2) {
            throw new IllegalArgumentException();
        }

        notifyObservers();
        return expression;
    }

    /**
     * проверка валидности выражения
     * */
    private void validationExpression(char[] charArray) {
        if (!isCorrectExpressionRegularitySignNumber(charArray)) {
            throw new IllegalArgumentException();
        }

        if (!isCorrectCountBrackets(charArray)) {
            throw new IllegalArgumentException();
        }

        if (!isCorrectRegularityBrackets(charArray)) {
            throw new IllegalArgumentException();
        }

        if (!checkExpression(charArray)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * проверка не встречается ли подряд два арифметических символа
     * */
    private boolean isCorrectExpressionRegularitySignNumber(char[] charArray) {
        for (int i = 0; i < charArray.length-1; i++) {
            if (isOperationSign(charArray[i]) && isOperationSign(charArray[i+1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * равно ли число открыывающих и закрывающих кавычек
     * */
    private boolean isCorrectCountBrackets(char[] charArray) {
        int countBrackets = 0;

        for (char c : charArray) {
            if (String.valueOf(c).equals("(")) {
                countBrackets += 1;
            }

            if (String.valueOf(c).equals(")")) {
                countBrackets -= 1;
            }
        }

        if (countBrackets != 0) {
            return false;
        }

        return true;
    }

    /**
     * правильно расставлены кавычки
     * сначала "(" потом ")"
     * */
    private boolean isCorrectRegularityBrackets(char[] charArray) {
        int count = 0;

        for (char c : charArray) {
            if (String.valueOf(c).equals("(")) {
                count += 1;
            }

            if (String.valueOf(c).equals(")")) {
                count -= 1;
            }

            if (count < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * не является ли последний симвом в выражении арифметической операцией
     * */
    private boolean checkExpression(char[] charArray) {
        return !isOperationSign(charArray[charArray.length-1]);
    }

    /**
     * является ли симвом арифметической операцией
     * */
    private static boolean isOperationSign(final char c) {
        return "+-*/^".indexOf(c) != -1;
    }

    /**
     * является ли симвом цифрой
     * */
    private static boolean isDigit(final char c) {
        return "0123456789.".indexOf(c) != -1;
    }

    /**
     * является ли симвом арифметическим
     * */
    private static boolean isSign(final char c) {
        return "+-*/^()".indexOf(c) != -1;
    }

    /**
     * является ли симвом знаком "-"
     * */
    private static boolean isSignMinus(final char c) {
        return "-".indexOf(c) != -1;
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
