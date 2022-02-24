package com.imaleex.Models.Personas;

import com.imaleex.Models.Mascotas.Mascota;
import com.imaleex.Utils.Validator;

import java.util.ArrayList;

public abstract class Persona {
    private String nombre;
    private String direccion;
    private String telefono;
    protected ArrayList<Mascota> listaMascotas;

    public Persona(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        if (Validator.checkName(nombre))
            this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        if (Validator.checkTel(telefono))
            this.nombre = nombre;
    }
    public ArrayList<Mascota> getListaMascotas() {return listaMascotas;}
    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }
}
