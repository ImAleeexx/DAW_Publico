package com.company;

import java.util.Comparator;

public class PersonaDateComparator implements Comparator<Persona> {
    @Override
    public int compare(Persona o1, Persona o2) {
        return o1.getBirthDate().compareTo(o2.getBirthDate());
    }
}