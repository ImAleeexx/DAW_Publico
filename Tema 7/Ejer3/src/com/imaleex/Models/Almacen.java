package com.imaleex.Models;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Operations.Compra;
import com.imaleex.Models.Operations.Operation;
import com.imaleex.Models.Operations.Venta;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Almacen {

    private double saldoAlmacen = 100;
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Operation> listaOperaciones = new ArrayList<Operation>();

    public Almacen() {}

    public double getSaldoAlmacen() {
        return saldoAlmacen;
    }

    public boolean addVenta (Venta v){
       return ventas.add(v);
    }

    public boolean addProducto (Producto p){
        return productos.add(p);
    }
    public Producto searchProducto (String nombre){
        for (Producto p: productos) {
            if (p.getName().equalsIgnoreCase(nombre))
                return p;
        }
        return null;
    }
    public ArrayList<Producto> getListaProductos(){
        return this.productos;
    }

    public boolean parseOperation (Operation o) throws OperationException {
        switch ( o.getClass().getSimpleName()){
            case "Venta":
               return processVenta((Venta) o);
            case "Compra":
               return processCompra((Compra) o);
            default:
                throw new OperationException("El tipo de operacion no esta definido");

        }
    }

    private boolean processVenta(Venta v) throws OperationException {
        Producto p = v.getProducto();
        if (p.getQuantity() < v.getQuantity())
            throw new OperationException("El producto no tiene suficientes articulos para vender");
        if (v.getQuantity() < 1)
            throw new OperationException("Introduce valores positivos para la cantidad");
        double operationCost = v.getQuantity() * v.getUnitaryPrice();
        this.saldoAlmacen = this.saldoAlmacen + operationCost;
        p.setQuantity(p.getQuantity() - v.getQuantity());

        return listaOperaciones.add(v);
    }

    private boolean processCompra(Compra c) throws OperationException {
        double operationCost = c.getUnitaryPrice() * c.getQuantity();
        if (c.getQuantity() < 1)
            throw new OperationException("Introduce valores positivos para la cantidad");
        if (operationCost > saldoAlmacen)
            throw new OperationException("El almacen no tiene suficiente dinero para esta compra");
        Producto p = c.getProducto();
        p.setQuantity(p.getQuantity() + c.getQuantity());
        if (c.getUnitaryPrice() != p.getUnitaryPrice()){
            p.setUnitaryPrice(c.getUnitaryPrice());
        }
        this.saldoAlmacen = this.saldoAlmacen - operationCost;
        return listaOperaciones.add(c);
    }
}
