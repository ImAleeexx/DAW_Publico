package com.imaleex.Models.Personas;

import com.imaleex.Models.Mascotas.Mascota;

import java.util.ArrayList;

public class Cliente extends Persona {

    public Cliente(String nombre, String direccion, String telefono) {
        super(nombre, direccion, telefono);
        listaMascotas = new ArrayList<>();
    }


    public void addMascota(Mascota m) {
        listaMascotas.add(m);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + getNombre() + '\'' +
                ", direccion='" + getDireccion() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                '}';
    }
}
