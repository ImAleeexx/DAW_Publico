package com.imaleex.Models.Mascotas;


import java.time.LocalDate;

public class Gato extends Mascota {
    public Gato(String nombre, LocalDate fechaNac, boolean sexo, double peso, double longitud, String tipoPelo) {
        super(nombre, fechaNac, sexo, peso, longitud, tipoPelo);
    }
}
