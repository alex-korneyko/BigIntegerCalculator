package ua.goit.gojava;

import ua.goit.gojava.arithmetic.BigCompute;
import ua.goit.gojava.gui.MainWindow;
import ua.goit.gojava.parser.Parser;

import java.awt.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Parser parser = new Parser();
        BigCompute bigCompute = new BigCompute();

        if(args.length ==0) {
            MainWindow mainWindow = new MainWindow("Calculator", new Dimension(600, 400));
            mainWindow.init();
        }else{
            try {
                String result = bigCompute.compute(parser.toBigInteger(Arrays.toString(args))).toString();
                System.out.println(result);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}

