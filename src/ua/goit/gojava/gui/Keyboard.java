package ua.goit.gojava.gui;

import ua.goit.gojava.Observer;
import ua.goit.gojava.Observable;
import ua.goit.gojava.parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.2 20.04.2016
 */
public class Keyboard extends JPanel implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private String keyboardBuffer = "";
    boolean equallyPressed = false;

    public  Keyboard() {
        setLayout(new GridBagLayout());
        setVisible(true);

        createKeys();


    }

    public void setBuffer(String string){
        keyboardBuffer = string;
    }

    private void createKeys() {

        JButton buttonC = new JButton("C");
        add(buttonC, new GridBagConstraints(0, 0, 5, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonC.setForeground(Color.RED);
        buttonC.addActionListener(new ClearButtonActionListener());

        JButton buttonBack = new JButton(String.valueOf((char) 8592));
        add(buttonBack, new GridBagConstraints(5, 0, 2, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonBack.addActionListener(new BackButtonActionListener());

        JButton buttonOpenParenthesis = new JButton("(");
        add(buttonOpenParenthesis, new GridBagConstraints(7, 0, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonOpenParenthesis.addActionListener(new OperationButtonActionListener());

        JButton buttonCloseParenthesis = new JButton(")");
        add(buttonCloseParenthesis, new GridBagConstraints(8, 0, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonCloseParenthesis.addActionListener(new OperationButtonActionListener());

        JButton button5 = new JButton("5");
        add(button5, new GridBagConstraints(0, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button5.addActionListener(new NumButtonActionListener());

        JButton button6 = new JButton("6");
        add(button6, new GridBagConstraints(1, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button6.addActionListener(new NumButtonActionListener());

        JButton button7 = new JButton("7");
        add(button7, new GridBagConstraints(2, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button7.addActionListener(new NumButtonActionListener());

        JButton button8 = new JButton("8");
        add(button8, new GridBagConstraints(3, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button8.addActionListener(new NumButtonActionListener());

        JButton button9 = new JButton("9");
        add(button9, new GridBagConstraints(4, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        button9.addActionListener(new NumButtonActionListener());

        JButton buttonPlus = new JButton("+");
        add(buttonPlus, new GridBagConstraints(5, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonPlus.addActionListener(new OperationButtonActionListener());

        JButton buttonMultiply = new JButton("*");
        add(buttonMultiply, new GridBagConstraints(6, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonMultiply.addActionListener(new OperationButtonActionListener());

        JButton buttonPower = new JButton("^");
        add(buttonPower, new GridBagConstraints(7, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonPower.addActionListener(new OperationButtonActionListener());

        JButton buttonSqr = new JButton("^2");
        add(buttonSqr, new GridBagConstraints(8, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonSqr.addActionListener(new OperationButtonActionListener());

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
            if (keyboardBuffer.equals("") && e.getActionCommand().equals("0")) {
                return;
            }
            keyboardBuffer += e.getActionCommand();
            notifyObservers();
        }
    }

    public class OperationButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            keyboardBuffer += e.getActionCommand();
            notifyObservers();
        }
    }

    public class ClearButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            keyboardBuffer = "";
            notifyObservers();
        }
    }

    public class ResultButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            equallyPressed = true;
            notifyObservers();
        }
    }

    public class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (keyboardBuffer.length() > 0) {
                keyboardBuffer = keyboardBuffer.substring(0, keyboardBuffer.length() - 1);
                notifyObservers();
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
            if (equallyPressed) {
                if (observer instanceof Parser) {
                    observer.update(keyboardBuffer);
                    keyboardBuffer = "";
                }
            } else {
                if (observer instanceof Screen) {
                    observer.update(keyboardBuffer);
                }
            }
        }
        equallyPressed = false;
    }
}
