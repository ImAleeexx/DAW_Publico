package com.imaleex.Views;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Main;
import com.imaleex.Models.Producto;
import com.imaleex.Models.Operations.Venta;
import com.imaleex.Utils.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

/**
 * @author Alex Cortes
 */
public class SellWindow {
    private JTextField textFieldProducto;
    private JTextField textFieldQuantity;
    private JTextField textFieldUnitaryPrice;
    private JButton salirButton;
    private JButton aceptarButton;
    private JPanel MainPanel;
    private JButton rellenoAleatorioButton;
    private JButton limpiarDatosButton;
    private JList<String> listaProductos;
    private JTextField textFieldAvQuantity;
    private JFrame frame = new JFrame("Panel de venta");


    public SellWindow() {
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //Salida desde boton
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main.showMenu();
            }
        });


        //Boton de aceptar
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto p = Main.searchProducto(listaProductos.getSelectedValue());
                if (createVenta(p))
                    Utils.showInfoMessage("La operacion se ha realizado con exito");
            }
        });

        textFieldQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println(textFieldQuantity.getText());
            }
        });
        listaProductos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(listaProductos.getSelectedValue());
                Producto p = Main.searchProducto(listaProductos.getSelectedValue());
                textFieldUnitaryPrice.setText(String.valueOf(p.getUnitaryPrice()));
                textFieldAvQuantity.setText(String.valueOf(p.getQuantity()));
            }
        });
    }

    private boolean createVenta(Producto p){
        try {
            Venta v = new Venta(p, Integer.parseInt(textFieldQuantity.getText()));
            return Main.a.parseOperation(v);
        }catch (OperationException e){
            Utils.showErrorMessage(e.getMessage());
        } catch (Exception e){
            Utils.showErrorMessage("Ha ocurrido un error al realizar la venta");
        }
    return  false;
    }

    private void createUIComponents() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Producto p: Main.getListaProductos()) {
            listModel.addElement(p.getName());
        }
        this.listaProductos = new JList<>(listModel);
        this.listaProductos.setSelectionMode(SINGLE_SELECTION);
    }
}
