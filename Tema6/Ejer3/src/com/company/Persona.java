package com.company;

import com.company.exceptions.NameValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Persona {
    private String name;
    private String city;
    private LocalDate birthDate;

    public Persona(String name, String city, LocalDate birthDate) throws NameValidationException {
        if (validName(name))
            this.name = name;
        else
            throw new NameValidationException("El nombre introducido no es valido");
        if (validName(city))
            this.city = city;
        else
            throw new NameValidationException("La ciudad introducida no es valida");
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getStringBirthDate() {
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private boolean validName(String name) {
        return  name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    }
}
