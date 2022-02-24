package com.imaleex.Models;

import com.imaleex.Exceptions.DatosNoValidos;
import com.imaleex.Utils.Validator;

import javax.swing.*;

/**
 * @author Alex Cortes
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private String dni;

    public Persona(String nombre, String apellidos, String dni) {
        try {
            setNombre(nombre);
            setApellidos(apellidos);
            setDni(dni);
        } catch (DatosNoValidos e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws DatosNoValidos {
        if (Validator.checkName(nombre)){
            this.nombre = nombre;
        }  else
            throw new DatosNoValidos("El nombre no tiene un formato valido");
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws DatosNoValidos {
        if (Validator.checkName(nombre)){
            this.apellidos = apellidos;
        }  else
            throw new DatosNoValidos("El apellido no tiene un formato valido");
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DatosNoValidos {
        if (Validator.checkDni(dni)){
            this.dni = dni;
        } else
            throw new DatosNoValidos("El dni no tiene un formato valido");
    }
}
