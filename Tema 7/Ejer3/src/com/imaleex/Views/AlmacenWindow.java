package com.imaleex.Views;

import com.imaleex.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alex Cortes
 */
public class AlmacenWindow {
    private JTextField textFieldApellidos;
    private JButton comprarButton;
    private JButton venderButton;
    private JPanel MainPanel;
    private JButton salirButton;
    private JTextField textFieldSaldo;
    private JFrame frame = new JFrame("Panel de almacen");


    public AlmacenWindow() {
        this.textFieldSaldo.setText(String.valueOf(Main.a.getSaldoAlmacen()));
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new SellWindow();
            }
        });
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CompraWindow();
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
