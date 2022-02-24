package com.imaleex;

import com.imaleex.Exceptions.AlumnoNoEncontradoException;
import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Models.Alumno;
import com.imaleex.Models.Curso;
import com.imaleex.Utils.Faker;
import com.imaleex.Views.MainWindow;

import java.util.ArrayList;

public class Main {
    public static Curso[] listaCursos = new Curso[16];
    public static ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
    public static void main(String[] args) throws DatoNoValidoException {
	populateCourses();
    listaAlumnos.add(new Alumno("Alex", "Cortes", "72845634B", listaCursos[0]));
    MainWindow.main();
    }

    private static void populateCourses(){
        char[] courseChars =  {'A','B','C','D'};
        int arrPos = 0;
        for (int i = 1; i <= 4; i++) {
            for (char c: courseChars) {
                listaCursos[arrPos] = new Curso(String.valueOf(i)+String.valueOf(c));
                arrPos++;
            }
        }
    }

    public static Alumno searchAlumno(String dni) throws AlumnoNoEncontradoException {
        for (Alumno a: listaAlumnos) {
            if (a.getDni().equalsIgnoreCase(dni))
                return a;
        }
        throw new AlumnoNoEncontradoException();
    }

    public static Curso searchCurso(String codigoCurso) throws DatoNoValidoException {
        for (Curso c: listaCursos) {
            if (c.getCodigo().equalsIgnoreCase(codigoCurso))
                return c;
        }
        throw new DatoNoValidoException("El curso no es valido");

    }

    public static Curso getRandCurso() {
        return listaCursos[Faker.randomPosNumber(listaCursos.length)];
    }


    public static boolean deleteAlumno(Alumno a)  {
        return listaAlumnos.remove(a);
    }

}
