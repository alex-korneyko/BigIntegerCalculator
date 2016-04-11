package ua.goit.gojava;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {

    private String title;
    private Dimension dim;

    private Screen screen = new Screen();
    private Keyboard keyboard = new Keyboard();

    public MainWindow(String title, Dimension dim) {
        this.title = title;
        this.dim = dim;
    }

    public void init() {
        setTitle(title);
        setSize(dim.width, dim.height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(true);

        screen.init();
        keyboard.init();
        keyboard.regObserver(screen);

        add(screen, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        add(keyboard, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        pack();
    }
}
