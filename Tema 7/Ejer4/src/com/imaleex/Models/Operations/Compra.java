package com.imaleex.Models.Operations;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Externos.Proveedor;
import com.imaleex.Models.Producto;

/**
 * @author Alex Cortes
 */
public class Compra extends Operation {
    private double unitaryPrice;
    private Proveedor proveedor;
    public Compra(Producto producto, int quantity, double unitaryPrice) throws OperationException {
        super(producto, quantity);
        this.unitaryPrice = unitaryPrice;
        if (unitaryPrice <= 0){
            throw new OperationException("El precio por unidad no es valido");
        }
    }

    @Override
    public double getUnitaryPrice() {
        return unitaryPrice;
    }
}
