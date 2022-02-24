package com.imaleex.Models.Operations;

import com.imaleex.Models.Producto;

/**
 * @author Alex Cortes
 */
public class Compra extends Operation {
    private double unitaryPrice;
    public Compra(Producto producto, int quantity, double unitaryPrice) {
        super(producto, quantity);
        this.unitaryPrice = unitaryPrice;
    }

    @Override
    public double getUnitaryPrice() {
        return unitaryPrice;
    }
}
