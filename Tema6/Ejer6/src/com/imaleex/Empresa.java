package com.imaleex;

import com.imaleex.persons.Cliente;
import com.imaleex.persons.Empleado;
import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public boolean addEmpleado(Empleado empleado) {
        return empleados.add(empleado);
    }

    public boolean addCliente(Cliente cliente) {
        return clientes.add(cliente);
    }

}
