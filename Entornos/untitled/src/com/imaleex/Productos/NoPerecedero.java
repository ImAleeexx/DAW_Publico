package com.imaleex.Productos;

public class NoPerecedero extends Producto {
    private int tipo;

    public NoPerecedero(String nombre, double precio, int tipo) {
        super(nombre, precio);
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public void calcular() {

    }
}
