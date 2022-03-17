package com.imaleex.Views;

import javax.swing.*;

/**
 * @author Alex Cortes
 */
public class CreateWindow {
    private JPanel barPanel;
    private JMenu gestionarEventosMenu;
    private JMenuItem crearEventoMenuItem;
    private JMenuItem cancelarEventoMenuItem;
    private JMenu consultarEventosMenu;
    private JPanel PanelPrincipal;
    JFrame frame = new JFrame("Gestion Eventos");


    public CreateWindow() {
        this.frame.setContentPane(this.PanelPrincipal);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

}
