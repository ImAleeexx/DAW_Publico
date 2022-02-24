package com.imaleex.Models.Mascotas;

import java.time.LocalDate;

public abstract class Mascota {
    private String nombre;
    private LocalDate fechaNac;
    private boolean sexo;
    private double peso;
    private double longitud;
    private String tipoPelo;

    public Mascota(String nombre, LocalDate fechaNac, boolean sexo, double peso, double longitud, String tipoPelo) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.peso = peso;
        this.longitud = longitud;
        this.tipoPelo = tipoPelo;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public boolean getSexo() {
        return sexo;
    }

    public double getPeso() {
        return peso;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getTipoPelo() {
        return tipoPelo;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNac + '\'' +
                ", sexo=" + sexo +
                ", peso=" + peso +
                ", longitud=" + longitud +
                ", tipoPelo='" + tipoPelo + '\'' +
                '}';
    }
}
