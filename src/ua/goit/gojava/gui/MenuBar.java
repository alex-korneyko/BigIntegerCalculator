package ua.goit.gojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 16.04.2016.
 */
public class MenuBar extends JMenuBar {
    private AboutWindow aboutWindow = new AboutWindow();

    public MenuBar(){
        JMenu menuHelp = new JMenu("Help");
        JMenuItem helpMenuItemAbout = new JMenuItem("About");
        menuHelp.add(helpMenuItemAbout);

        JMenu menuFile = new JMenu("File");
        JMenuItem fileMenuItemExit = new JMenuItem("Exit");
        menuFile.add(fileMenuItemExit);

        this.add(menuFile);
        this.add(menuHelp);

        fileMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        helpMenuItemAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboutWindow.showAbout();
            }
        });
    }


}
