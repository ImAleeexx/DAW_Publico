package com.imaleex.Models;

import com.imaleex.Models.Externos.Proveedor;

/**
 * @author Alex Cortes
 */
public class Producto {
    private String name;
    private Proveedor proveedor;
    private int quantity;
    private double unitaryPrice;


    public Producto(String name, int quantity, double unitaryPrice, Proveedor proveedor) {
        this.name = name;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        this.proveedor = proveedor;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getImporte(int quantity){
        return unitaryPrice * quantity;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }
    public double getSellingUnitaryPrice() {
        return unitaryPrice * 2;
    }
    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
}
