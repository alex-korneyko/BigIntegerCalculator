package ua.goit.gojava;

import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    class G1 extends JFrame
//implements ActionListener
    {
        static final String opList[] = {"+", "-", "*", "/"};
        private JComboBox<String> opComboBox;
        private JButton applyButton = new JButton("Execute!");
        private GridLayout eLayout = new GridLayout(0,2,2,2);
        private JPanel panel = new JPanel(eLayout);
        private JTextField numF=new JTextField(15);
        private JTextField numS=new JTextField(15);
        private JTextField numR=new JTextField(15);
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");

        public G1(String name) {
            super(name);
            setBounds(200, 200, 400, 300);
            setResizable(false);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        private void createMenu() {


            for (String fileItem : new String [] { "New", "Exit" }) {
                JMenuItem item = new JMenuItem(fileItem);
                item.setActionCommand(fileItem.toLowerCase());
                item.addActionListener(new NewMenuListener());
                fileMenu.add(item);
            }
            fileMenu.insertSeparator(1);

            menu.add(fileMenu);

            for (String aboutItem : new String [] { "Text" }) {
                JMenuItem item = new JMenuItem(aboutItem);
                item.setActionCommand(aboutItem.toLowerCase());
                item.addActionListener(new NewMenuListener());
                aboutMenu.add(item);
            }
            fileMenu.insertSeparator(1);

            menu.add(aboutMenu);
            setJMenuBar(menu);
        }

        private class NewMenuListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if ("exit".equals(command)) {
                    System.exit(0);
                }
                if ("new".equals(command)) {
                    numF.setText("0");
                    numS.setText("0");
                }
            }
        }

        private class ClickListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            }
        }


        public void addComponentsToPane(final Container pane) {
            opComboBox = new JComboBox<String>(opList);

            createMenu();

            //Add buttons to experiment with Grid Layout
            panel.add(new JLabel("Value 1:"));
            numF.setText("0");
            numF.addActionListener(new ClickListener());
            panel.add(numF);

            panel.add(new JLabel("Value 2:"));
            numS.setText("0");
//        numS.addActionListener(new ClickListener());
            panel.add(numS);

            panel.add(new JLabel("Result:"));
            numR.setText("0");
            panel.add(numR);

            JPanel controls = new JPanel();
            controls.setLayout(new GridLayout(2,3));

            controls.add(new JLabel("Operation:"));
            controls.add(new JLabel(" "));
            controls.add(opComboBox);
            controls.add(applyButton);

            //Process the Apply gaps button press
            applyButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String opBox = (String)opComboBox.getSelectedItem();
                    JOptionPane.showMessageDialog(null, "Your choice -"+opBox, "Operation", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            pane.add(panel, BorderLayout.NORTH);
            pane.add(new JSeparator(), BorderLayout.CENTER);
            pane.add(controls, BorderLayout.SOUTH);
        }

        private static void createAndShowGUI() {
            //Create and set up the window.
            G1 frame = new G1("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Set up the content pane.
            frame.addComponentsToPane(frame.getContentPane());
            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            createAndShowGUI();
        }
    }

