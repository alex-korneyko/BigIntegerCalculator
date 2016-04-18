package ua.goit.gojava.gui;

import ua.goit.gojava.Observer;

import javax.swing.*;


public class Screen extends JTextField implements Observer {

    public Screen() {
        setEditable(false);
        setText("0");
        setHorizontalAlignment(RIGHT);
    }

    @Override
    public void update(Object o) {

        String toDisplay = (String) o;

        if (toDisplay.length() == 0) {
            setText("0");
        } else {
            setText((String) o);
        }
    }
}
