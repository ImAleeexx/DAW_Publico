package com.imaleex.Views;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Main;
import com.imaleex.Models.Cliente;
import com.imaleex.Utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Alex Cortes
 */

public class LoginForm extends JDialog {
    private JPanel contentPane;
    private JButton bEntrar;
    private JTextField tfDNI;
    private JTextField tfContrasena;
    private JButton b3;
    private JButton b5;
    private JButton b4;
    private JButton b2;
    private JButton b1;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b10;
    private JLabel lDNI;
    private JLabel lContasena;
    private JButton bBorrarContrasena;

    public LoginForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(bEntrar);
        randomNumeration();
        tfContrasena.setEditable(false);


        bEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfContrasena.getText().length() == 6){
                    try {
                        Cliente c = Main.loginCliente(tfDNI.getText(), tfContrasena.getText());
                        Utils.showInfoMessage(c.getName());
                        dispose();
                        MainWindow.main(c);
                    } catch (DatoNoValidoException ex) {
                        Utils.showErrorMessage(ex.getMessage());
                        tfContrasena.setText("");
                    }
                } else {
                    Utils.showErrorMessage("La contraseña no cumple con los requisitos");
                }
            }
        });

        bBorrarContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfContrasena.setText("");
            }
        });

        this.pack();
        this.setVisible(true);
    }


    private void randomNumeration() {
        JButton[] buttons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10};
        Integer[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        List<Integer> listaNumeros = Arrays.asList(numeros);

        Collections.shuffle(listaNumeros);
        listaNumeros.toArray(numeros);
        int settedBtn = 0;
        for (Integer n:listaNumeros){
            buttons[settedBtn].setText(String.valueOf(n));
            buttons[settedBtn].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tfContrasena.getText().length() < 6){
                        tfContrasena.setText(tfContrasena.getText() + textoBoton(e));
                    }
                }
            });
            settedBtn++;
        }


    }

    private String textoBoton(ActionEvent e) {
        return ((JButton) e.getSource()).getText();
    }

}