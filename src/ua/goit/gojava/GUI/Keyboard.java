package ua.goit.gojava.gui;

import ua.goit.gojava.*;
import ua.goit.gojava.Observer;
import ua.goit.gojava.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;


public class Keyboard extends Panel implements ua.goit.gojava.Observable {

    private List<Observer> observers = new ArrayList<>();
    String stringExpression = "";


    public void init() {
        setLayout(new GridBagLayout());
        setVisible(true);

        createKeys();
    }


    private void createKeys() {

        JButton buttonC = new JButton();
        buttonC.setText("C");
        add(buttonC, new GridBagConstraints(0, 0, 5, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonC.setForeground(Color.RED);
        buttonC.addActionListener(new ClearButtonActionListener());

        JButton buttonRndInt = new JButton();
        buttonRndInt.setText("Rnd Int");
        add(buttonRndInt, new GridBagConstraints(5, 0, 2, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonRndInt.addActionListener(new RndButtonActionListener());

        JButton buttonRndBigInt = new JButton();
        buttonRndBigInt.setText("Rnd BigInt");
        add(buttonRndBigInt, new GridBagConstraints(7, 0, 2, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonRndBigInt.addActionListener(new RndButtonActionListener());

        JButton button5 = new JButton();
        button5.setText("5");
        add(button5, new GridBagConstraints(0, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button5.addActionListener(new NumButtonActionListener());

        JButton button6 = new JButton();
        button6.setText("6");
        add(button6, new GridBagConstraints(1, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button6.addActionListener(new NumButtonActionListener());

        JButton button7 = new JButton();
        button7.setText("7");
        add(button7, new GridBagConstraints(2, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button7.addActionListener(new NumButtonActionListener());

        JButton button8 = new JButton();
        button8.setText("8");
        add(button8, new GridBagConstraints(3, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button8.addActionListener(new NumButtonActionListener());

        JButton button9 = new JButton();
        button9.setText("9");
        add(button9, new GridBagConstraints(4, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button9.addActionListener(new NumButtonActionListener());

        JButton buttonPlus = new JButton();
        buttonPlus.setText("+");
        add(buttonPlus, new GridBagConstraints(5, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonPlus.addActionListener(new OperationButtonActionListener());

        JButton buttonMultiply = new JButton();
        buttonMultiply.setText("*");
        add(buttonMultiply, new GridBagConstraints(6, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonMultiply.addActionListener(new OperationButtonActionListener());

        JButton buttonPower = new JButton();
        buttonPower.setText("^");
        add(buttonPower, new GridBagConstraints(7, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonPower.addActionListener(new OperationButtonActionListener());

        JButton buttonBack = new JButton();
        buttonBack.setText("<<");
        add(buttonBack, new GridBagConstraints(8, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonBack.addActionListener(new BackButtonActionListener());

        JButton button0 = new JButton();
        button0.setText("0");
        add(button0, new GridBagConstraints(0, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button0.addActionListener(new NumButtonActionListener());

        JButton button1 = new JButton();
        button1.setText("1");
        add(button1, new GridBagConstraints(1, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button1.addActionListener(new NumButtonActionListener());

        JButton button2 = new JButton();
        button2.setText("2");
        add(button2, new GridBagConstraints(2, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button2.addActionListener(new NumButtonActionListener());

        JButton button3 = new JButton();
        button3.setText("3");
        add(button3, new GridBagConstraints(3, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button3.addActionListener(new NumButtonActionListener());

        JButton button4 = new JButton();
        button4.setText("4");
        add(button4, new GridBagConstraints(4, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button4.addActionListener(new NumButtonActionListener());

        JButton buttonMinus = new JButton();
        buttonMinus.setText("-");
        add(buttonMinus, new GridBagConstraints(5, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonMinus.addActionListener(new OperationButtonActionListener());

        JButton buttonDivide = new JButton();
        buttonDivide.setText("/");
        add(buttonDivide, new GridBagConstraints(6, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonDivide.addActionListener(new OperationButtonActionListener());

        JButton buttonResult = new JButton();
        buttonResult.setText("=");
        add(buttonResult, new GridBagConstraints(7, 2, 2, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonResult.addActionListener(new ResultButtonActionListener());
    }

    public class NumButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (stringExpression.equals("") && e.getActionCommand().equals("0")) {
                return;
            }
            stringExpression += e.getActionCommand();
            notifyObservers();
        }
    }

    public class OperationButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            stringExpression += e.getActionCommand();
            notifyObservers();
        }
    }

    public class ClearButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            stringExpression = "";
            notifyObservers();


        }
    }

    public class RndButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("Rnd Int")) {

            } else {

            }

        }
    }

    public class ResultButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Observer observer : observers) {
                if (observer instanceof Parser) {
                    observer.update(stringExpression);
                }
            }

            stringExpression = "";
//            notifyObservers();
        }
    }

    public class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            stringExpression = stringExpression.substring(0, stringExpression.length() - 1);
            notifyObservers();
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
            if (observer instanceof Screen) {
                observer.update(stringExpression);
            }
        }
    }
}
