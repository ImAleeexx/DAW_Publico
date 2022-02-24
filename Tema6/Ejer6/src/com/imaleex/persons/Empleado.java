package com.imaleex.persons;

public class Empleado extends Persona{
    protected double sueldoBruto;
    public Empleado(String nombre, int edad, double sueldoBruto) {
        this.edad = edad;
        this.nombre = nombre;
        this.sueldoBruto = sueldoBruto;
    }

    @Override
    public String mostrar() {
        return  "Nombre :" + nombre + " " +  "Edad :" + edad + " " +  "Sueldo Bruto :" + sueldoBruto;
    }

    public double calcularSalario(){
        return this.sueldoBruto;
    }
}
