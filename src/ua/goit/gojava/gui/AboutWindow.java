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
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        setVisible(false);
        setUndecorated(true);

        JButton buttonOk = new JButton("Ok") {{
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hideAbout();
                }
            });

        }};

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        textPane.setText("<div style=\"text-align: center;\"><span style=\"color:#A52A2A\"><span style=\"font-size:18px\"><strong>BigInteger Calculator</strong></span></span></div>\n" +
                "\n" +
                "<div style=\"text-align: center;\"><span style=\"font-size:12px\"><strong>Калькулятор для чисел произвольной длинны</strong></span></div>\n" +
                "\n" +
                "<div style=\"text-align: center;\"><span style=\"font-size:12px\"><strong>и произвольного количества операндов</strong></span></div>\n" +
                "\n" +
                "<div style=\"text-align: center;\">\n" +
                "<hr /></div>\n" +
                "\n" +
                "<div><span style=\"font-size:14px\"><span style=\"color:#0000FF\"><strong>Над проектом работала г</strong></span></span><span style=\"color:#0000FF\"><strong style=\"line-height:1.6em\"><span style=\"font-size:14px\">руппа №5.</span></strong></span></div>\n" +
                "\n" +
                "<div><span style=\"color:#0000FF\"><strong style=\"line-height:1.6em\"><span style=\"font-size:14px\">В составе:</span></strong></span></div>\n" +
                "\n" +
                "<div><a href=\"https://www.facebook.com/Alex.Korneyko\" target=\"_blank\"><strong>Александр Корнейко</strong></a> - Концепция, проектирование и общаяя координация, GUI.</div>\n" +
                "\n" +
                "<div><a href=\"https://www.facebook.com/anton.petrov.92560\"><strong>Антон Петров</strong></a> - Реализация класса BigInteger</div>\n" +
                "\n" +
                "<div><a href=\"https://www.facebook.com/ihor.pylyavets\"><strong>Игорь Пилявец</strong></a> - Реализация класса BigCompute</div>\n" +
                "\n" +
                "<div><a href=\"https://www.facebook.com/dmytro.chumack\"><strong>Дмитрий Чумак</strong></a> - Реализация класса Parser</div>\n" +
                "\n" +
                "<div>\n" +
                "<hr />\n" +
                "<div>&copy; <span style=\"color:#A52A2A\"><strong><em>Dris4eCoders</em> <em>GangstaGroup</em></strong></span>.</div>\n" +
                "\n" +
                "<div>For <a href=\"http://goit.ua/\">GoIT</a>&nbsp;company</div>\n" +
                "\n" +
                "<div><strong>2016</strong></div>\n" +
                "</div>\n");


        add(textPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        add(buttonOk, new GridBagConstraints(0, 1, 1, 1, 0.5, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));


        pack();
    }

    public void showAbout() {
        setVisible(true);
    }

    public void hideAbout() {
        setVisible(false);
    }

}
