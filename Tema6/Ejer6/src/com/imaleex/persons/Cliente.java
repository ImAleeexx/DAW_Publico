package com.imaleex.persons;

public class Cliente extends Persona{
    protected String telefono;
    public Cliente(String nombre, int edad, String telefono) {
        this.edad = edad;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public String mostrar() {
        return  "Nombre :" + nombre + " " +  "Edad :" + edad + " " +  "Telefono :" + telefono;
    }
}
