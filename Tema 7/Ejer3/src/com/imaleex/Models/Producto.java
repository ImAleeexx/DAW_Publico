package com.imaleex.Models;

/**
 * @author Alex Cortes
 */
public class Producto {
    private String name;
    private int quantity;
    private double unitaryPrice;


    public Producto(String name, int quantity, double unitaryPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
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

    public void setUnitaryPrice(double unitaryPrice) {
        this.unitaryPrice = unitaryPrice;
    }
}
