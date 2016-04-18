package ua.goit.gojava.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 17.04.2016.
 */
public class AboutWindow extends JFrame {

    public AboutWindow() throws HeadlessException {

        setTitle("About");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(false);
        setUndecorated(true);

        add(new JButton("Ok"){{
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hideAbout();
                }
            });
        }});


        //pack();
    }

    public void showAbout() {
        setVisible(true);
    }

    public void hideAbout(){
        setVisible(false);
    }

}
