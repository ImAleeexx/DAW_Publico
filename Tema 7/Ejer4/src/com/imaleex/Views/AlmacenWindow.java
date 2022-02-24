package com.imaleex.Views;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Main;
import com.imaleex.Models.Externos.Cliente;
import com.imaleex.Models.Externos.Proveedor;
import com.imaleex.Models.Operations.Compra;
import com.imaleex.Models.Operations.Venta;
import com.imaleex.Models.Producto;
import com.imaleex.Utils.Faker;
import com.imaleex.Utils.Utils;
import javafx.scene.input.KeyCode;

import javax.management.OperationsException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Alex Cortes
 */
public class AlmacenWindow {
    private JPanel panel1;
    private JRadioButton rbCompra;
    private JRadioButton rbVenta;
    private JTextField tfProducto;
    private JTextField tfUnidades;
    private JLabel lAlmacen;
    private JLabel lNombre;
    private JLabel lUnidades;
    private JTextField tfPrecio;
    private JTextField tfImporte;
    private JComboBox cbProveedor;
    private JLabel lPrecio;
    private JLabel lProveedor;
    private JLabel lImporte;
    private JTextField tfCliente;
    private JCheckBox cbVolumen;
    private JCheckBox cbProntoPago;
    private JLabel lPrecioV;
    private JTextField tfPrecioV;
    private JLabel lCliente;
    private JPanel jpVentas;
    private JPanel jpCompra;
    private JPanel jpContabilizar;
    private JLabel lImporteVenta;
    private JTextField tfImporteV;
    private JButton bAceptar;
    private JButton bCancelar;
    private JButton productosButton;
    private JFrame frame = new JFrame("Panel de venta");
    private Producto selectedProduct = null;


    public AlmacenWindow() {
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        rellenarProveedor();

        rbVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpCompra.setVisible(false);
                jpVentas.setVisible(true);
            }
        });
        rbCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpCompra.setVisible(true);
                jpVentas.setVisible(false);
            }
        });
        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productos = "";
                for (Producto p: Main.getListaProductos()) {
                    productos += p.getName() + "\n";
                }
                Utils.showInfoMessage(productos);
            }
        });
        tfProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Producto p = Main.searchProducto(tfProducto.getText());
                if (p != null){
                    selectedProduct = p;
                    tfProducto.setText(p.getName());
                    tfPrecio.setText(String.valueOf(p.getUnitaryPrice()));
                    tfPrecioV.setText(String.valueOf(p.getSellingUnitaryPrice()));
                }

            }
        });
        tfUnidades.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                populateValues();

            }
        });
        cbProntoPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateValues();
            }
        });

        cbVolumen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateValues();
            }
        });
        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (rbVenta.isSelected()){
                        Venta v = generateVenta();
                        if (v != null && Main.processOperation(v)){
                            Utils.showInfoMessage("La venta se ha realizado con exito");
                        }
                    }

                    if (rbCompra.isSelected()){
                        Compra c = generateCompra();
                        if (c != null && Main.processOperation(c)){
                            Utils.showInfoMessage("La compra se ha realizado con exito");
                        }
                    }
                } catch (OperationException exception){
                    Utils.showErrorMessage(exception.getMessage());
                }

            }
        });
    }

    public void rellenarProveedor(){
        cbProveedor.removeAllItems();
        for (Proveedor p: Main.getListaProveedores()) {
            cbProveedor.addItem(p.getName());
        }
        cbProveedor.setSelectedIndex(-1);
    }

    public double calcularImporteVenta(int unidades){
        double totalBaseCost = selectedProduct.getSellingUnitaryPrice() * unidades ;
        double totalCost = totalBaseCost;
        if (cbProntoPago.isSelected()){
            totalCost -= totalBaseCost*0.02 ;
        }
        if (cbVolumen.isSelected()){
            totalCost -= totalBaseCost *0.03 ;
        }
        return totalCost;
    }

    private void populateValues(){
        try {
            if (selectedProduct != null && Integer.parseInt(tfUnidades.getText()) > 0){
                tfImporteV.setText(String.valueOf(calcularImporteVenta(Integer.parseInt(tfUnidades.getText()))));
                tfImporte.setText(String.valueOf(selectedProduct.getUnitaryPrice() * Integer.parseInt(tfUnidades.getText())));
            }
        } catch (Exception ignored){}
    }

    private Venta generateVenta() {
        try {
            if (selectedProduct == null){
                throw new OperationException("El producto no es valido");
            }
            int unidades = Integer.parseInt(tfUnidades.getText());
            if (unidades <= 0){
                throw new OperationException("Las unidades del producto son invalidas");
            }
            Cliente c = Main.searchCliente(tfCliente.getText());
            if (c == null){
                c = new Cliente(tfCliente.getText(), Faker.fullAddress());
            }
            boolean[] discounts = {cbProntoPago.isSelected(), cbVolumen.isSelected()};
            return new Venta(selectedProduct, unidades, c, discounts);

        } catch (OperationException e) {
            Utils.showErrorMessage(e.getMessage());
        } catch (NumberFormatException  e) {
            Utils.showErrorMessage("Las unidades del producto son invalidas");
        }  catch (Exception ignored) {}
        return null;
    }


    private Compra generateCompra() {
        try {
            if (selectedProduct == null){
                throw new OperationException("El producto no es valido");
            }
            int unidades = Integer.parseInt(tfUnidades.getText());
            if (unidades <= 0){
                throw new OperationException("Las unidades del producto son invalidas");
            }
            double precioUnitario = Double.parseDouble(tfPrecio.getText());
            Proveedor p = Main.searchProveedor(String.valueOf(cbProveedor.getSelectedItem()));
            if (p == null){
                throw new OperationException("El proveedor no se ha seleccionado");
            }
            return new Compra(selectedProduct, unidades, precioUnitario);

        } catch (OperationException e) {
            Utils.showErrorMessage(e.getMessage());
        } catch (NumberFormatException  e) {
            Utils.showErrorMessage("Las unidades del producto son invalidas");
        } catch (Exception ignored) {}
        return null;
    }

}
