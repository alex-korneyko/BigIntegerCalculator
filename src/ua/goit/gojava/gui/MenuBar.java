package ua.goit.gojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 16.04.2016.
 */
public class MenuBar extends JMenuBar {

    AboutWindow aboutWindow;

    JMenu menuFile = new JMenu("File");
    JMenu menuHelp = new JMenu("Help");

    JMenuItem fileMenuItemExit = new JMenuItem("Exit");

    JMenuItem helpMenuItemAbout = new JMenuItem("About");

    public MenuBar(AboutWindow aboutWindow) {
        this.aboutWindow = aboutWindow;
    }

    public void init(){

        this.add(menuFile);
        this.add(menuHelp);

        menuFile.add(fileMenuItemExit);

        menuHelp.add(helpMenuItemAbout);



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
