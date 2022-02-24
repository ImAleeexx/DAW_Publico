package com.imaleex;


import com.imaleex.Exceptions.TipoNoValido;
import com.imaleex.Models.Mascotas.Gato;
import com.imaleex.Models.Mascotas.Mascota;
import com.imaleex.Models.Mascotas.Perro;
import com.imaleex.Models.Personas.Cliente;
import com.imaleex.Models.Personas.Persona;
import com.imaleex.Models.Personas.Veterinario;
import com.imaleex.Utils.Faker;
import com.imaleex.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<Cliente> listaClientes = new ArrayList<>();
    public static ArrayList<Veterinario> listaVeterinarios = new ArrayList<>();
    public static ArrayList<Mascota> listaMascotas = new ArrayList<>();

    public static void main(String[] args) {

        solicitarClientes();
        solicitarVeterinarios();
        solicitarMascotas();
        showStartMenu();
    }


    public static void showStartMenu() {
        String menuInfo = "               MENU PRINCIPAL\n" +
                "Lista de opciones disponibles: \n" +
                "a) Mostrar el numero de clientes que poseen un tipo de animal concreto.\n" +
                "b) Datos personales del cliente a partir del nombre de mascota. \n" +
                "c) Datos del veterinario a partir de los datos de una mascota.\n" +
                "d) Datos de las mascotas de un cliente.\n" +
                "e) Datos de las mascotas de un veterinario.\n" +
                "f) Apagar maquina\n\n";
        String menuInput = Utils.inputString(menuInfo, true, null).toLowerCase();
        parseMenuInput(menuInput.toCharArray()[0]);
    }

    public static void parseMenuInput(char c) {
        switch (c) {
            case 'a':
                getCountByMascotaType();
                break;
            case 'b':
                displayMascotaOwner();
                break;
            case 'c':
                displayMascotaVet();
                break;
            case 'd':
                Cliente cliente = findCliente();
                if (cliente != null)
                    displayMascotasOfPersona(cliente);
                break;
            case 'e':
                Veterinario veterinario = findVet();
                if (veterinario != null)
                    displayMascotasOfPersona(veterinario);
                break;
            case 'f':
                System.exit(0);
                break;
            default:
                Utils.showErrorMessage("La opcion introducida no esta definida");
                showStartMenu();

                break;
        }
        showStartMenu();
    }

    public static void getCountByMascotaType() {
        try {
            String getMascotaType = Utils.inputString("Tipo de mascota [perro/gato]", false, null);
            if (getMascotaType.equalsIgnoreCase("perro") || getMascotaType.equalsIgnoreCase("gato")) {
                boolean isPerro = getMascotaType.equalsIgnoreCase("perro");
                int n = 0;
                for (Cliente c : listaClientes) {
                    if (c.getListaMascotas().stream().anyMatch(m -> (m instanceof Perro && isPerro) || (m instanceof Gato && !isPerro)))
                        n++;
                }
            } else
                throw new TipoNoValido("El tipo introducido no es ni perro ni gato");
        } catch (TipoNoValido e) {
            Utils.showErrorMessage(e.getMessage());
        } catch (Exception e) {
            Utils.showErrorMessage("El dato introducido no es valido");
        }
    }

    //Metodos de busqueda de modelos
    public static Mascota findMascota() {
        String nombre = Utils.inputString("Nombre de la mascota", true, null);
        Mascota mascota = null;
        for (Mascota m : listaMascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre))
                mascota = m;
        }
        if (mascota == null)
            Utils.showErrorMessage("No se ha encontrado una mascota con ese nombre");
        return mascota;
    }

    public static Cliente findCliente() {
        String nombre = Utils.inputString("Nombre del cliente", true, null);
        Cliente cliente = null;
        for (Cliente c : listaClientes) {
            if (c.getNombre().equalsIgnoreCase(nombre))
                cliente = c;
        }
        if (cliente == null)
            Utils.showErrorMessage("No se ha encontrado un cliente con ese nombre");
        return cliente;
    }

    public static Veterinario findVet() {
        String nombre = Utils.inputString("Nombre del veterinario", true, null);
        Veterinario veterinario = null;
        for (Veterinario v : listaVeterinarios) {
            if (v.getNombre().equalsIgnoreCase(nombre))
                veterinario = v;
        }
        if (veterinario == null)
            Utils.showErrorMessage("No se ha encontrado un veterinario con ese nombre");
        return veterinario;
    }

    //Metodos de busqueda de atributos de mascotas
    public static Cliente searchMascotaOwner(Mascota m) {
        List<Cliente> l = listaClientes.stream().filter(c -> c.getListaMascotas().contains(m)).toList();
        Cliente cliente = l.get(0);
        if (cliente == null)
            Utils.showErrorMessage("No se ha encontrado un cliente con esa mascota");
        return cliente;
    }

    public static Veterinario searchMascotaVet(Mascota m) {
        List<Veterinario> l = listaVeterinarios.stream().filter(v -> v.getListaMascotas().contains(m)).toList();
        Veterinario veterinario = l.get(0);
        if (veterinario == null)
            Utils.showErrorMessage("No se ha encontrado un veterinario con esa mascota");
        return veterinario;
    }

    //Metodos de muestreo de atributos de mascota
    public static void displayMascotaOwner() {
        Mascota m = findMascota();
        Cliente c = null;
        if (m != null)
            c = searchMascotaOwner(m);

        if (c != null)
            Utils.showInfoMessage(c.toString());
    }

    public static void displayMascotaVet() {
        Mascota m = findMascota();
        Veterinario v = null;
        if (m != null)
            v = searchMascotaVet(m);

        if (v != null)
            Utils.showInfoMessage(v.toString());
    }


    //Metodos de muestreo de atributos de personas
    public static void displayMascotasOfPersona(Persona p) {
        if (p != null) {
            for (Mascota m : p.getListaMascotas()) {
                Utils.showInfoMessage(m.toString());
            }
        }
    }


    public static void solicitarClientes() {
        do {
            listaClientes.add(new Cliente(
                    Utils.inputString("Nombre", true, Faker.firstName()),
                    Utils.inputString("Dirección", true, Faker.streetName()),
                    Utils.inputString("Teléfono", true, Faker.getPhoneNumber())
            ));
        } while (!Utils.inputBoolean("Finalizar insertado de clientes"));
    }

    public static void solicitarVeterinarios() {
        do {
            listaVeterinarios.add(new Veterinario(
                    Utils.inputString("Nombre", true, Faker.firstName()),
                    Utils.inputString("Dirección", true,  Faker.streetName()),
                    Utils.inputString("Teléfono", true, Faker.getPhoneNumber()),
                    Utils.inputString("DNI", true, Faker.getDni()),
                    Utils.inputString("Número seguridad social", true, Faker.getSegSocial())
            ));
        } while (!Utils.inputBoolean("Finalizar insertado de veterinarios"));
    }


    public static void solicitarMascotas() {
        do {
            String mascotaType = "";
            do {
                mascotaType = Utils.inputString("\tTipo [perro/gato]", true, "perro");
            } while (mascotaType.equalsIgnoreCase("perro") || mascotaType.equalsIgnoreCase("gato"));

            Mascota m;
            if (mascotaType.equalsIgnoreCase("perro")) {
                m = new Perro(
                        Utils.inputString("Nombre", true, null),
                        Utils.inputDate("Fecha de nacimiento"),
                        Utils.inputBoolean("Es macho?"),
                        Utils.inputDouble("Peso"),
                        Utils.inputDouble("Longitud"),
                        Utils.inputString("Tipo de pelo", true, null)
                );
            } else {
                m = new Gato(
                        Utils.inputString("Nombre", true, null),
                        Utils.inputDate("Fecha de nacimiento"),
                        Utils.inputBoolean("Es macho?"),
                        Utils.inputDouble("Peso"),
                        Utils.inputDouble("Longitud"),
                        Utils.inputString("Tipo de pelo", true, null)
                );
            }
            listaMascotas.add(m);

            int cliente = -1, veterinario = -1;

            do {
                cliente = Utils.inputInt("\tDueño de la mascota [0-" + listaClientes.size() + "]");
            } while (cliente < 0 || cliente >= listaClientes.size());

            do {
                veterinario = Utils.inputInt("\tVeterinario de la mascota [0-" + listaVeterinarios.size() + "]");
            } while (veterinario < 0 || veterinario >= listaVeterinarios.size());

            listaClientes.get(cliente).addMascota(m);
            listaVeterinarios.get(veterinario).addMascota(m);

        } while (!Utils.inputBoolean("Finalizar insertado de mascotas"));
    }


}
