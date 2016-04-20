package ua.goit.gojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.2 20.04.2016
 */
public class MenuBar extends JMenuBar {
    private AboutWindow aboutWindow = new AboutWindow();

    public MenuBar(JFormattedTextField textField, Keyboard keyboard) {

        JMenu menuFile = new JMenu("File");
        JMenuItem fileMenuItemExit = new JMenuItem("Exit");
        menuFile.add(fileMenuItemExit);

        JMenu menuEdit = new JMenu("Edit");
        JMenuItem editMenuItemCopy = new JMenuItem("Copy");
        JMenuItem editMenuItemPaste = new JMenuItem("Paste");
        menuEdit.add(editMenuItemCopy);
        menuEdit.add(editMenuItemPaste);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem helpMenuItemAbout = new JMenuItem("About");
        menuHelp.add(helpMenuItemAbout);

        this.add(menuFile);
        this.add(menuEdit);
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

        editMenuItemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection strToClip = new StringSelection(textField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(strToClip, null);
            }
        });

        editMenuItemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                try {
                    String clpb = (String) clipboard.getData(DataFlavor.stringFlavor);
                    textField.setText(clpb);
                    keyboard.setBuffer(clpb);
                } catch (UnsupportedFlavorException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
