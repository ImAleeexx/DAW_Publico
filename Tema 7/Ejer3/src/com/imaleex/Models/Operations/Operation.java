package com.imaleex.Models.Operations;

import com.imaleex.Models.Producto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Alex Cortes
 */
public abstract class Operation {
    private Producto producto;
    private int quantity;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Operation(Producto producto, int quantity) {
        this.producto = producto;
        this.quantity = quantity;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitaryPrice() {
        return producto.getUnitaryPrice();
    }
}
