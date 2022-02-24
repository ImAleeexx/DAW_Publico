package com.imaleex.Models.Operations;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Producto;

import java.time.LocalDateTime;

/**
 * @author Alex Cortes
 */
public abstract class Operation {
    private Producto producto;
    private int quantity;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Operation(Producto producto, int quantity) throws OperationException {
        this.producto = producto;
        this.quantity = quantity;
        if (quantity<=0){
            throw new OperationException("Las unidades deben tener valor positivo");
        }
        if (producto == null){
            throw new OperationException("El producto no esta definido");
        }
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
