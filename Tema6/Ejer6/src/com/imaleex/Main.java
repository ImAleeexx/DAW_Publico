package com.imaleex;

import com.imaleex.Utils.Faker;
import com.imaleex.persons.Directivo;
import com.imaleex.persons.Empleado;

public class Main {
    public static void main(String[] args) {
        System.out.println("Nombre: " + Faker.firstName());
        System.out.println("Apellido: " + Faker.lastName());
        System.out.println("Nombre Completo: " + Faker.fullName());
        System.out.println("DNI: " + Faker.getDni());
        System.out.println("Num Seg Social: " + Faker.getSegSocial());
        System.out.println("Ciudad: " + Faker.city());
        System.out.println("Provincia: " + Faker.provinceName());
        System.out.println("CP: " + Faker.postalCode());
        System.out.println("Direccion simple: " + Faker.streetName());
        System.out.println("Direccion compuesta: " + Faker.composedStreetName());
        System.out.println("Direccion completa: " + Faker.fullAddress());
        System.out.println("Sufijo para direccion: " + Faker.streetSufix());
    }


    public static void populate(){

    }
}
