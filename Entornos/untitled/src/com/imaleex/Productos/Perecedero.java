package com.imaleex.Productos;

public class Perecedero extends Producto {
    private int diasCaducar;

    public Perecedero(String nombre, double precio, int diasCaducar) {
        super(nombre, precio);
        this.diasCaducar = diasCaducar;
    }

    public int getDiasCaducar() {
        return diasCaducar;
    }

    public void setDiasCaducar(int diasCaducar) {
        this.diasCaducar = diasCaducar;
    }

    @Override
    public void calcular() {

    }
}
