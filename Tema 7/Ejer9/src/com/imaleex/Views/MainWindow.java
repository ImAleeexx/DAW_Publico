package com.imaleex.Views;

import com.imaleex.Models.Cliente;
import com.imaleex.Models.Cuenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class MainWindow {
    private JPanel MainPanel;
    private JMenuBar menuBar1;
    private JMenu resumenMenu;
    private JMenu tarjetasMenu;
    private JMenu inversionesMenu;
    private JMenu mercadosMenu;
    private JMenu prestamosMenu;
    private JMenu segurosMenu;
    private JMenu serviciosMenu;
    private JPanel barPanel;
    private JButton CANCELARButton;
    private JMenu cuentasMenu;
    private JMenuItem consultaMenuItem;
    private JMenuItem movimientosMenuItem;
    private JPanel buttonPanel;
    private JPanel cuentasPanel;
    public JFrame frame = new JFrame("MainWindow");
    private Cliente cliente;


    public MainWindow(Cliente c) {
        this.cliente = c;
        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void loadAccounts(){
        ArrayList<Cuenta> accountList = this.cliente.getAccountList();
        for (Cuenta c: accountList) {

        }
    }

    public static void main(Cliente c) {
        MainWindow mainWindow = new MainWindow(c);
        mainWindow.frame.setContentPane(mainWindow.MainPanel);
        mainWindow.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.frame.pack();
        mainWindow.frame.setVisible(true);
    }
}
