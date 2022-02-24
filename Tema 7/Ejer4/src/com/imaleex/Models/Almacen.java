package com.imaleex.Models;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Externos.Cliente;
import com.imaleex.Models.Externos.Proveedor;
import com.imaleex.Models.Operations.Compra;
import com.imaleex.Models.Operations.Operation;
import com.imaleex.Models.Operations.Venta;
import com.imaleex.Utils.Utils;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Almacen {

    private double saldoAlmacen = 10000;
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
    private ArrayList<Operation> listaOperaciones = new ArrayList<Operation>();
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

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

    public boolean addProveedor (Proveedor p){
        return listaProveedores.add(p);
    }
    public ArrayList<Proveedor> getListaProveedor (){
        return listaProveedores;
    }

    public Proveedor searchProveedor (String nombre){
        for (Proveedor p: listaProveedores) {
            if (p.getName().equalsIgnoreCase(nombre))
                return p;
        }
        return null;
    }

    public Producto searchProducto (String nombre){
        for (Producto p: productos) {
            if (p.getName().equalsIgnoreCase(nombre))
                return p;
        }
        return null;
    }

    public Cliente searchCliente (String nombre){
        for (Cliente c: listaClientes) {
            if (c.getName().equalsIgnoreCase(nombre))
                return c;
        }
        return null;
    }
    public ArrayList<Producto> getListaProductos(){
        return this.productos;
    }

    public boolean parseOperation (Operation o) throws OperationException {
        if (o == null){
            throw new OperationException("Comprueba los campos de la operación");
        }
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
            throw new OperationException("El producto no tiene suficientes articulos para vender, compra antes de vender");
        if (v.getQuantity() < 1)
            throw new OperationException("Introduce valores positivos para la cantidad, compra antes de vender");
        this.saldoAlmacen = this.saldoAlmacen + v.getTotalCost();
        p.setQuantity(p.getQuantity() - v.getQuantity());
        if (!listaClientes.contains(v.getCliente())){
            listaClientes.add(v.getCliente());
        }
        Utils.showInfoMessage("La venta se ha completado con un importe de " + v.getTotalCost());
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
        Utils.showInfoMessage("La compra se ha completado con un importe de " + operationCost);
        return listaOperaciones.add(c);
    }
}
