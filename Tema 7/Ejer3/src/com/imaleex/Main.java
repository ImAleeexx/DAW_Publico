package com.imaleex;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Models.Almacen;
import com.imaleex.Models.Producto;
import com.imaleex.Utils.Faker;
import com.imaleex.Views.AlmacenWindow;

import java.util.ArrayList;

public class Main {
    public static Almacen a = new Almacen();
    public static void main(String[] args) throws DatoNoValidoException {
        if (a.getListaProductos().size() <= 0){
            generateProducts();
        }
        showMenu();
    }
    public static ArrayList<Producto> getListaProductos(){
        return a.getListaProductos();
    }
    public static Producto searchProducto(String nombre){
        return a.searchProducto(nombre);
    }

    private static void generateProducts(){
        for (int i = 0; i < 5; i++){
            Producto p = new Producto(
                    Faker.getProductName(),
                    0,
                    Faker.randomPosNumber(40)+1
            );
            if (a.searchProducto(p.getName()) == null){
                a.addProducto(p);
            } else{
                i--;
            }


        }
    }

    public static void showMenu(){
        new AlmacenWindow();
    }


}
