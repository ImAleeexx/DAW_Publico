package com.imaleex;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Almacen;
import com.imaleex.Models.Externos.Cliente;
import com.imaleex.Models.Externos.Proveedor;
import com.imaleex.Models.Operations.Operation;
import com.imaleex.Models.Producto;
import com.imaleex.Utils.Faker;
import com.imaleex.Views.AlmacenWindow;

import java.util.ArrayList;

public class Main {
    public static Almacen a = new Almacen();
    public static void main(String[] args) throws DatoNoValidoException {

        if (a.getListaProductos().size() <= 0){
            generateProviders();
        }
        if (a.getListaProductos().size() <= 0){
            generateProducts();
        }
        if (a.getListaProductos().size() <= 0){
            //generateClients();
        }
        showMenu();
    }
    public static ArrayList<Producto> getListaProductos(){
        return a.getListaProductos();
    }
    public static Producto searchProducto(String nombre){
        return a.searchProducto(nombre);
    }

    public static Cliente searchCliente(String nombre){
        return a.searchCliente(nombre);
    }
    public static boolean processOperation(Operation o) throws OperationException {
        return a.parseOperation(o);
    }
    public static ArrayList<Proveedor> getListaProveedores(){
        return a.getListaProveedor();
    }
    public static Proveedor searchProveedor(String nombre){
        return a.searchProveedor(nombre);
    }

    private static void generateProviders(){
        for (int i = 0; i < 5; i++){
            Proveedor p = new Proveedor(Faker.fullBusinessName(), Faker.fullAddress() );
            a.addProveedor(p);
        }
    }

    private static void generateProducts(){
        for (int i = 0; i < 5; i++){
            Producto p = new Producto(
                    Faker.getProductName(),
                    10,
                    Faker.randomPosNumber(40)+1,
                    a.getListaProveedor().get(i));
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
