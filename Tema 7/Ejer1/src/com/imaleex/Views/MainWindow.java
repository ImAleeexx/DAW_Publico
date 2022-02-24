package com.imaleex.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alex Cortes
 */
public class MainWindow {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton aceptarButton;
    private JButton salirButton;

    public MainWindow() {
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
