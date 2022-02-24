package com.imaleex.persons;

import java.util.ArrayList;

public class Directivo extends Empleado{
    ArrayList<Empleado> listaSubordinados = new ArrayList<Empleado>();
    String categoria;

    public Directivo(String nombre, int edad, double sueldoBruto, String categoria) {
        super(nombre, edad, sueldoBruto);
        this.categoria = categoria;
    }


    @Override
    public String mostrar() {
        return  "Nombre :" + nombre + " " +  "Edad :" + edad + " " +  "Categoria :" + categoria + " " + "Sueldo Bruto :" + sueldoBruto ;
    }
}
