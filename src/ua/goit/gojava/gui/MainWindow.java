package ua.goit.gojava.gui;

import ua.goit.gojava.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.4 20.04.2016
 */
public class MainWindow extends JFrame {

    public Screen screen = new Screen();

    public MainWindow(String title, Dimension dim, Observer o) {
        Keyboard keyboard = new Keyboard();
        keyboard.regObserver(o);
        setTitle(title);
        setSize(dim.width, dim.height);

        JScrollPane scrollPane = new JScrollPane(screen, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuBar menuBar = new MenuBar(screen, keyboard);
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(true);

        keyboard.regObserver(screen);

        add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        add(keyboard, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        pack();
    }

    public Observer getScreen() {
        return screen;
    }
}
