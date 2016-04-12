package ua.goit.gojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static java.awt.GridBagConstraints.*;


public class Keyboard extends Panel implements ua.goit.gojava.Observable {

    private List<ua.goit.gojava.Observer> observers = new ArrayList<>();
    String stringExpression = "";
    String operand = "";


    public void init() {
        setLayout(new GridBagLayout());
        setVisible(true);

        createKeys();
    }


    private void createKeys() {

        JButton buttonC = new JButton();
        buttonC.setText("C");
        add(buttonC, new GridBagConstraints(0, 0, 4, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonC.addActionListener(new ClearButtonActionListener());

        JButton buttonCE = new JButton();
        buttonCE.setText("CE");
        add(buttonCE, new GridBagConstraints(4, 0, 4, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        buttonCE.addActionListener(new ClearButtonActionListener());

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

        JButton buttonMultiply = new JButton();
        buttonMultiply.setText("*");
        add(buttonMultiply, new GridBagConstraints(6, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton buttonBack = new JButton();
        buttonBack.setText("<<");
        add(buttonBack, new GridBagConstraints(7, 1, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
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

        JButton buttonDivide = new JButton();
        buttonDivide.setText("/");
        add(buttonDivide, new GridBagConstraints(6, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton buttonResult = new JButton();
        buttonResult.setText("=");
        add(buttonResult, new GridBagConstraints(7, 2, 1, 1, 1, 1, NORTH, HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
    }

    public class NumButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (operand.equals("") && e.getActionCommand().equals("0")) {
                return;
            }
            operand += e.getActionCommand();
            notifyObservers();
        }
    }

    public class OperationButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            stringExpression += operand;
            operand="";
            notifyObservers();
        }
    }

    public class ClearButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "C":
                    operand = "";
                    notifyObservers();
                    break;
                case "CE":
                    operand = "";
                    notifyObservers();
                    break;

            }

        }
    }

    public class BackButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            operand = operand.substring(0, operand.length() - 1);
            notifyObservers();
        }
    }

    @Override
    public void regObserver(ua.goit.gojava.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ua.goit.gojava.Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ua.goit.gojava.Observer observer : observers) {
            observer.update(operand);
        }
    }
}
