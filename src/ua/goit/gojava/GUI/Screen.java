package ua.goit.gojava.gui;

import ua.goit.gojava.Observer;

import java.awt.*;


public class Screen extends TextField implements Observer {

    public void init() {
        setText("0");
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
