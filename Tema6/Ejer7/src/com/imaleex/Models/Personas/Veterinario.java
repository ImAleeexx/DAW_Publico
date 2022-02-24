package com.imaleex.Models.Personas;
import com.imaleex.Exceptions.TipoNoValido;
import com.imaleex.Models.Mascotas.Mascota;
import com.imaleex.Utils.Validator;

import java.util.ArrayList;

public class Veterinario extends Persona {
    private String dni;
    private String segSocial;

    public Veterinario(String nombre, String direccion, String telefono, String dni, String segSocial)  {
        super(nombre, direccion, telefono);
        setDni(dni);
        setSegSocial(segSocial);
        listaMascotas = new ArrayList<>();
    }

    private void setDni(String dni){
        if (Validator.checkDni(dni)){
            this.dni = dni;
        }
    }

    private void setSegSocial(String segSocial){
        if (Validator.checkSegSoc(segSocial)){
            this.segSocial = segSocial;
        }
    }
    public String getDni() {
        return dni;
    }

    public String getSegSocial() {
        return segSocial;
    }



    public void addMascota(Mascota mas) {
        listaMascotas.add(mas);
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "nombre='" + this.getNombre() + '\'' +
                ", direccion='" + this.getDireccion() + '\'' +
                ", telefono='" + this.getTelefono() + '\'' +
                ", dni='" + this.getDni() + '\'' +
                ", segSocial='" + this.getSegSocial() + '\'' +
                '}';
    }
}
