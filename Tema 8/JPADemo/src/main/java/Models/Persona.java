package Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personas", schema = "tema8", catalog = "")
public class Persona {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dni")
    private String dni;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni) && Objects.equals(nombre, persona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre);
    }
}
