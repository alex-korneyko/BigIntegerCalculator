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
 * Created by Chumack Dmitro on 19.04.2016.
 */
public class Parser implements Observer, Observable {

    private List<Observer> observers = new ArrayList<>();
    Expression expression;
    boolean error;

    /**
     * ����� ������ �� ������ ������������� "���������" (������ ������ Expression).
     * ��������� ����� ���� ����� ������, � ������������ ������ ���������
     * �������������� ��������: '+' '-' '*' '/' '^'
     * ������ ������� ������ Expression, ���������� ��� ����� � ����� ��������, �� ������� �������
     * ������ ExpressionElement, � ��������� � ��������� ����� ������� Expression.
     * �������� ���������� �������� ������� � �������� ������ ParametrizedParserTest,
     * ������ ��� ����� ������ �� "��"
     * � ������ ��������� ������������ ������, ������ ��������������� ���������� IllegalArgumentException.
     * �������� ������������ ����� ������� � �������� ������ ParametrizedParserTest2,
     * ��� ����� ������ ������ �� "��"
     * <p>
     * ������ ��������� ��������� ��������� ���������: Expression structure v1.0.jpg
     *
     * @param stringExpression ��������� ������
     * @return ������ ������ Expression
     */
    public Expression toBigInteger(String stringExpression) throws IllegalArgumentException {

        expression = new Expression();
        error = false;

        //������ ��� ���������� ������
        String number;
        int sign=0;
        int i=0;

        if ((stringExpression.length()==1)             //�������� �� �� ��� ��������� ���� ������ �� ���� - ��� ������!
                && ((stringExpression.charAt(0)=='+')
                || (stringExpression.charAt(0)=='-')
                || (stringExpression.charAt(0)=='*')
                || (stringExpression.charAt(0)=='/')
                || (stringExpression.charAt(0)=='^')))
        {   error=true;
            notifyObservers();
            return expression;
        }

        while (i<stringExpression.length())         //����������� ���� ������� � ����� ������
         {
            number="";
            String c=""+stringExpression.charAt(i);
                while (!(c.equals(ExpressionElementType.PLUS.toString())) &&    //������ ���� ���������� ������� � ������ ����� �� ������� � ������
                        !(c.equals(ExpressionElementType.MINUS.toString())) &&
                                !(c.equals(ExpressionElementType.MULTIPLY.toString())) &&
                                        !(c.equals(ExpressionElementType.DIVIDE.toString())) &&
                                          !(c.equals(ExpressionElementType.POWER.toString()))) {
                    number += c;                            //����������� �����
                    if (i < stringExpression.length() - 1) i++;
                    else break;
                    c = "" + stringExpression.charAt(i);
                }

                if (number.isEmpty()) {    //���� ������, ������ ��� ����
                    if (i==stringExpression.length() - 1) {error = true; break;} //���� � ��������� ������� - ������
                    else if (i==0) {
                        if (c.equals("+")) sign=0; //���� � ������ ����� "+" �� ��� ��,
                        if (c.equals("-")) sign=1; //���� "-" �� �������� sign=1
                        if ((c.equals("*")) || (c.equals("/")) || (c.equals("^"))) {error=true; break;} //����� ������
                        i++;
                    }
                    else {
                    switch(c) {  //����� ���� �������� � �������� � Expression
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
                        switch(stringExpression.charAt(i-1)) { //����� �������� ����� ��� ����� ������ - ��� ������
                            case '+':
                            case '-':
                            case '*':
                            case '/':
                            case '^':
                                error=true;
                        }
                        i++;
                    }
                } else {  //������������ �����
                    if (sign==1) number="-"+number; //���� ���� 1 - ������ ����� �������������
                    sign=0;
                     expression.elementSet.add(new ExpressionElement(new BigInteger(number)));

                    if ((i == stringExpression.length() - 1)//��������� �� �� ��� ��������� ������� - ���� - ������
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