package com.imaleex.Views;

import javax.swing.*;

/**
 * @author Alex Cortes
 */
public class MainWindow {
    private JPanel barPanel;
    private JMenu gestionarEventosMenu;
    private JMenuItem crearEventoMenuItem;
    private JMenuItem cancelarEventoMenuItem;
    private JMenu consultarEventosMenu;
    private JPanel PanelPrincipal;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    JFrame frame = new JFrame("Gestion Eventos");


    public MainWindow() {
        this.frame.setContentPane(this.PanelPrincipal);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

}
