package com.imaleex.persons;

public abstract class Persona implements IPersona{
    protected String nombre;
    protected int edad;
    public String getNombre(){
        return this.nombre;
    }
    public int getEdad(){
        return this.edad;
    }
}
