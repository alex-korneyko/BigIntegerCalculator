package ua.goit.gojava.gui;

import javax.swing.*;

/**
 * Created by admin on 16.04.2016.
 */
public class MenuBar extends JMenuBar {

    JMenu menuFile = new JMenu("File");
    JMenu menuHelp = new JMenu("Help");

    JMenuItem fileMenuItemExit = new JMenuItem("Exit");

    JMenuItem helpMenuItemAbout = new JMenuItem("About");

    public void init(){

        this.add(menuFile);
        this.add(menuHelp);

        menuFile.add(fileMenuItemExit);

        menuHelp.add(helpMenuItemAbout);
    }
}
