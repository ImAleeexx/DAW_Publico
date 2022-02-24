package com.imaleex;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Libro> libros = new ArrayList<Libro>();
    public static void main(String[] args) {
        libros.add(new Libro("123123123", "Josep y sus pelotas", "Josep el pedrerol", 109));
        libros.add(new Libro("981082939", "Roncero se pasa al barça", "Joan laporta", 111));
        Libro maxPages = null;
        for (Libro l : libros){
            if (maxPages == null)
                maxPages = l;
            else if (l.getPaginas() > maxPages.getPaginas())
                maxPages = l;
            System.out.println(l.toString());
        }

        System.out.println("El libro mas largo es " + maxPages.getTitulo() + " de " + maxPages.getAutor());
    }
}
