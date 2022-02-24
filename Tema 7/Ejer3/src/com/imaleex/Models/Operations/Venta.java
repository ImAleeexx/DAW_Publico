package com.imaleex.Models.Operations;

import com.imaleex.Models.Producto;

/**
 * @author Alex Cortes
 */
public class Venta extends Operation {
    public Venta(Producto producto, int quantity) {
        super(producto, quantity);
    }
}
