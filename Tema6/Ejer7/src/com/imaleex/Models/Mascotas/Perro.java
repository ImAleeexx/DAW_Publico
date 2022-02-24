package com.imaleex.Models.Mascotas;


import java.time.LocalDate;

public class Perro extends Mascota {
    public Perro(String nombre, LocalDate fechaNac, boolean sexo, double peso, double longitud, String tipoPelo) {
        super(nombre, fechaNac, sexo, peso, longitud, tipoPelo);
    }
}
