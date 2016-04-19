package ua.goit.gojava.gui;

import ua.goit.gojava.Observer;

import javax.swing.*;
import java.awt.*;


public class Screen extends JFormattedTextField implements Observer {

    public Screen() {
        setEditable(false);
        setText("0");
        setHorizontalAlignment(RIGHT);
        setFont(new Font("a", Font.BOLD, 16));

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
