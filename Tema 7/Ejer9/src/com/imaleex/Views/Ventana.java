package com.imaleex.Views;

import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Ventana {
    private JPanel pOperacion;
    private JPanel pCompra;
    private JPanel pVenta;
    private JPanel pProducto;
    private JPanel pBotones;
    private JPanel pTitulo;
    private JPanel pPrincipal;
    private JTextField tfNombreProducto;
    private JTextField tfUnidades;
    private JRadioButton rbCompra;
    private JRadioButton rbVenta;
    private JTextField tfPrecioCompra;
    private JComboBox cbProveedores;
    private JTextField tfImporteCompra;
    private JTextField tfPrecioVenta;
    private JTextField tfNombreCliente;
    private JCheckBox cbVolumen;
    private JCheckBox cbppp;
    private JTextField tfImporteVenta;
    private JButton bAceptar;
    private JButton bCancelar;
    private ButtonGroup bgOperacion;

    // Validaciones
    private boolean productoCorrecto;
    private boolean unidadesCorrectas;
    private boolean precioCompraCorrecto;
    private boolean nombreClienteCorrecto;

    private final  int DTOPP = 3;
    private final  int DTOV = 2;
    private Float importeVenta;

    public Ventana() {
        //pCompra.setVisible(false);
        //pVenta.setVisible(false);


        // actionPerformed
        tfNombreProducto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                // Validar el nombre del producto
                productoCorrecto = validarProducto();
            }
        });

        tfUnidades.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                // Validar unidades
                if ( productoCorrecto)
                    unidadesCorrectas = validarUnidades();
            }
        });

        rbCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprar();
            }
        });

        rbVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Main.hayUnidades(Integer.parseInt(tfUnidades.getText())))
                     vender();
                else
                {
                    JOptionPane.showMessageDialog(null, "No hay tantas unidades disponibles");
                    tfUnidades.setEditable(true);
                    tfUnidades.requestFocus();
                }
                // try
            }
        });

        tfPrecioCompra.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (productoCorrecto && unidadesCorrectas && rbCompra.isSelected()) {
                    precioCompraCorrecto = validarPrecioCompra();
                    if (precioCompraCorrecto)
                        calcularImporteCompra();
                }
            }
        });

        cbProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (precioCompraCorrecto && productoCorrecto && unidadesCorrectas && rbCompra.isSelected()) {
                        bAceptar.setEnabled(true);
                        bAceptar.requestFocus();
                    }
                }
        });

        bAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbCompra.isSelected())
                {
                    Main.guardarCompra(tfUnidades.getText(),tfPrecioCompra.getText());
                    limpiarCompra();
                }
                else
                {
                    // Doy por hecho que es venta
                    Main.guardarVenta(tfUnidades.getText());
                    limpiarVenta();
                }
            }
        });

        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tfNombreCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                nombreClienteCorrecto = validarNombreCliente();
                if (nombreClienteCorrecto)
                    bAceptar.setEnabled(true);
            }
        });

        cbVolumen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               calcularImporteVenta();

            }
        });


        cbppp.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               calcularImporteVenta();
            }
        });

    }

    public void calcularImporteVenta()
    {
        // En el Main?
        int dto=0;
        if (cbppp.isSelected())
            dto = DTOPP;

        if (cbVolumen.isSelected())
            dto += DTOV;

        tfImporteVenta.setText(String.valueOf(importeVenta - importeVenta*dto/100));
    }

    public boolean validarNombreCliente()
    {
        try {
                String cliente = tfNombreCliente.getText();
                if (cliente.isEmpty())
                    throw new Exception("El nombre del cliente es un dato obligatorio");

                Pattern patron = Pattern.compile("^[A-Z][a-z]+([ ][a-z]+)*$");
                Matcher m = patron.matcher(cliente);
                if (!m.matches())
                    throw new Exception("Nombre de cliente no válido");

                Main.buscarCliente(cliente);

            tfNombreCliente.setEditable(false);
            return true;
        }
        catch(Exception e)
        {
            mostrarError(e.getMessage());
            tfNombreCliente.setSelectionStart(0);
            tfNombreCliente.setSelectionEnd(tfNombreCliente.getText().length());
            tfNombreCliente.requestFocus();
            return false;
        }

    }
    public void limpiarCompra()
    {
        // Dejar la ventana como al principio
        tfNombreProducto.setText("");
        tfNombreProducto.setEditable(true);
        tfUnidades.setText("");
        tfUnidades.setEditable(true);
        //rbCompra.setSelected(false);
        bgOperacion.clearSelection();
        tfPrecioCompra.setText("");
        tfImporteCompra.setText("");
        cbProveedores.removeAllItems();
        tfImporteCompra.setText("");
        bAceptar.setEnabled(false);
        pVenta.setVisible(true);
        tfNombreProducto.requestFocus();
    }

    public void limpiarVenta()
    {
        // Dejar la ventana como al principio
        tfNombreProducto.setText("");
        tfNombreProducto.setEditable(true);
        tfUnidades.setText("");
        tfUnidades.setEditable(true);
        //rbCompra.setSelected(false);
        bgOperacion.clearSelection();
        tfPrecioVenta.setText("");
        tfNombreCliente.setText("");
        tfImporteVenta.setText("");
        cbppp.setSelected(false);
        cbVolumen.setSelected(false);
        bAceptar.setEnabled(false);
        pCompra.setVisible(true);
        tfNombreProducto.requestFocus();
    }

    public boolean validarProducto()
    {
        try {
           // if (!bCancelar.isFocusOwner()) {  // Todavía no está el cursor en el botón cancelar.
                String producto = tfNombreProducto.getText();
                if (producto.isEmpty())
                    throw new Exception("El nombre del producto es un dato obligatorio");

                Pattern patron = Pattern.compile("^[A-Z][a-z]+([ ][a-z]+)*$");
                Matcher m = patron.matcher(producto);
                if (!m.matches())
                    throw new Exception("Nombre de producto no válido");

                if (!Main.buscarProducto(producto))
                    throw new Exception("El producto indicado no existe");

                // }
                tfNombreProducto.setEditable(false);

            //}
            return true;
        }
        catch(Exception e)
        {
            mostrarError(e.getMessage());
            tfNombreProducto.setSelectionStart(0);
            tfNombreProducto.setSelectionEnd(tfNombreProducto.getText().length());
            tfNombreProducto.requestFocus();
            return false;
        }

    }

    public boolean validarUnidades()
    {
        // TODO: INTENTAR CREAR UN UNICO VALIDAR
        try {
            String unidades = tfUnidades.getText();
            if (unidades.isEmpty())
                throw new Exception("Las unidades son un dato obligatorio");

            Pattern patron = Pattern.compile("^[0-9]+$");
            Matcher m = patron.matcher(unidades);
            if (!m.matches())
                throw new Exception("Las unidades han de ser un número entero");

            if(Integer.parseInt(unidades) <= 0)
                throw new Exception("Las unidades tienen que ser mayores que cero");

            tfUnidades.setEditable(false);
            return true;
        }
        catch(Exception e)
        {
            mostrarError(e.getMessage());
            tfUnidades.setSelectionStart(0);
            tfUnidades.setSelectionEnd(tfUnidades.getText().length());
            tfUnidades.requestFocus();
            return false;
        }

    }

    public void mostrarError(String mensaje)
    {
        String[] botones = {"Aceptar"};
        JOptionPane.showOptionDialog(null,mensaje, "ERROR",
                JOptionPane.DEFAULT_OPTION ,JOptionPane.ERROR_MESSAGE , null , botones , botones[0]);
    }

    public void comprar()
    {
        pVenta.setVisible(false);
        pCompra.setVisible(true);
        llenarCombo(Main.getDatosProveedores());
        tfPrecioCompra.requestFocus();
    }

    public void vender()
    {
        pVenta.setVisible(true);
        pCompra.setVisible(false);
        tfPrecioVenta.setText(Main.getPrecio());
        importeVenta = Float.parseFloat(tfPrecioVenta.getText()) * Integer.parseInt(tfUnidades.getText());
        tfImporteVenta.setText(String.valueOf(importeVenta));
        tfNombreCliente.requestFocus();
    }

    // compras
    public void llenarCombo(String[] nombres)
    {
        cbProveedores.removeAllItems(); // Vaciar
        for(String n: nombres)
            cbProveedores.addItem(n);
        cbProveedores.setSelectedIndex(-1); //nada seleccionado.

    }

    public boolean validarPrecioCompra()
    {
        // TODO: INTENTAR CREAR UN UNICO VALIDAR
        try {
            String precio = tfPrecioCompra.getText();
            if (precio.isEmpty())
                throw new Exception("El precio de compra es un dato obligatorio");

            Pattern patron = Pattern.compile("^[0-9]+([.][0-9]*)?$");
            Matcher m = patron.matcher(precio);
            if (!m.matches())
                throw new Exception("El precio de compra es un dato numérico");

            if(Float.parseFloat(precio) <= 0)
                throw new Exception("El precio tiene que ser mayor que cero");

            return true;
        }
        catch(Exception e)
        {
            mostrarError(e.getMessage());
            tfPrecioCompra.setSelectionStart(0);
            tfPrecioCompra.setSelectionEnd(tfPrecioCompra.getText().length());
            tfPrecioCompra.requestFocus();
            return false;
        }

    }

    public void calcularImporteCompra()
    {
        float importe = Integer.parseInt(tfUnidades.getText()) * Float.parseFloat(tfPrecioCompra.getText());
        tfImporteCompra.setText(String.valueOf(importe));
    }

    public JPanel getpPrincipal()
    {
        return pPrincipal;
    }

}
