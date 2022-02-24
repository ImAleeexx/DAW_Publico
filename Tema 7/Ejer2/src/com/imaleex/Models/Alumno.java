package com.imaleex.Models;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Utils.Validator;

import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Alumno {

    private String nombre;
    private String apellidos;
    private String dni;
    private Curso curso;

    public Alumno(String nombre, String apellidos, String dni, Curso curso) throws DatoNoValidoException {
        setNombre(nombre);
        setApellidos(apellidos);
        setDni(dni);
        setCurso(curso);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws DatoNoValidoException {

        if (Validator.checkName(nombre)){
            this.nombre = nombre;
        } else
            throw new DatoNoValidoException("El nombre introducido no es valido."); // Check if Nombre is valid
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws DatoNoValidoException {
        if (Validator.checkName(apellidos)){
            this.apellidos = apellidos;
        } else
            throw new DatoNoValidoException("El apellido introducido no es valido"); // Check if Apellidos is valid
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DatoNoValidoException {
        if (Validator.checkDni(dni)){
            this.dni = dni;
        } else
            throw new DatoNoValidoException("El DNI introducido no ha podido ser validado."); // Check if DNI is valid
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
