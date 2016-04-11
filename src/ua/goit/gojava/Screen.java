package ua.goit.gojava;

import java.awt.*;


public class Screen extends TextField implements Observer {

    public void init() {
        setText("0");
    }

    @Override
    public void update(Object o) {

        setText((String) o);
    }
}
