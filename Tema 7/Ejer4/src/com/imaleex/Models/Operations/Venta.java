package com.imaleex.Models.Operations;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Externos.Cliente;
import com.imaleex.Models.Producto;
import com.imaleex.Models.Externos.Proveedor;

/**
 * @author Alex Cortes
 */
public class Venta extends Operation {
    private Cliente cliente;
    private boolean[] discounts = new boolean[2];
    public Venta(Producto producto, int quantity, Cliente cliente, boolean[] discounts) throws OperationException {
        super(producto, quantity);
        this.discounts = discounts;
        this.cliente = cliente;
        if (cliente == null || cliente.getName().isEmpty()){
            throw new OperationException("Error en el cliente de la venta");
        }

    }

    @Override
    public double getUnitaryPrice() {
        return (this.getProducto().getUnitaryPrice()) * 2;
    }
    public double getCostWithoutDiscount() {
        return getUnitaryPrice() * getQuantity();
    }
    public double getTotalCost() {
        double totalCost = getCostWithoutDiscount();
        if (discounts[0]){
            totalCost -= (getCostWithoutDiscount() * 0.02);
        }
        if (discounts[1]){
            totalCost -= (getCostWithoutDiscount() * 0.03);
        }
        return totalCost;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean[] getDiscounts() {
        return discounts;
    }

    public void processVenta(Venta v){
        Producto p = v.getProducto();
        double finalPrice = v.getTotalCost();
    }


}
