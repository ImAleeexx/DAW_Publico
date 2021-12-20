package com.company;


import com.company.exceptions.NameValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

import static com.company.Utils.parseDateString;

public class Main {
    static ArrayList<Persona> personas = new ArrayList<>();
    public static void main(String[] args) {

            boolean loop = true;
            while(loop){
                try {
                    String name = Utils.inputString("Introduce un nombre", false);
                    String city = Utils.inputString("Introduce la ciudad de " + name, false);
                    LocalDate date = Utils.inputDate("Introduce la fecha de nacimiento de " + name +"\n Formato: (dd/mm/aaaa)");
                    personas.add(new Persona(name, city, date));
                } catch (NameValidationException e){
                    Utils.showErrorMessage(e.getMessage());
                } catch (Exception e){
                    loop = false;
                }
            }

        System.out.println("Personas que viven en Elche: ");
        personas.forEach(persona -> {
            if (persona.getCity().equalsIgnoreCase("elche"))
                System.out.println(persona.getName() + "\n");
        });

        System.out.println("Personas mayores de edad: " +
                personas.stream().filter(persona -> {
                    return Period.between(persona.getBirthDate(), LocalDate.now()).getYears() > 17;
                }).count() + "\n");


        System.out.println("Persona mas mayor: " + getOldest(personas).getName());
    }

public static Persona getOldest(ArrayList<Persona>  personas){
    Collections.sort(personas, new PersonaDateComparator());
    return personas.get(0);
}


}
