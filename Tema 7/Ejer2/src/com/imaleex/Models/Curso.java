package com.imaleex.Models;

import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Curso {
    private String codigo;
    private ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();

    public Curso(String codigo, ArrayList<Alumno> listaAlumnos) {
        this.codigo = codigo;
        this.listaAlumnos = listaAlumnos;
    }

    public Curso(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }
}
