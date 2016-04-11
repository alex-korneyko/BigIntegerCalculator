package ua.goit.gojava;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        MainWindow mainWindow = new MainWindow("Calculator", new Dimension(600, 400));
        mainWindow.init();
    }
}

