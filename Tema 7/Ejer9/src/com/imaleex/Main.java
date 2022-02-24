package com.imaleex;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Models.Cliente;
import com.imaleex.Models.Cuenta;
import com.imaleex.Models.Operations.Movimiento;
import com.imaleex.Models.Operations.TipoMovimiento;
import com.imaleex.Utils.Utils;
import com.imaleex.Utils.Validator;

import java.util.ArrayList;


/**
 * @author Alex Cortes
 */
public class Main {
    private static ArrayList<Cliente> clientList = new ArrayList<Cliente>();

    public static void main(String[] args) throws DatoNoValidoException {
        generateClients();
        Cliente c1 = loginCliente("72845634B", "12313");
        Cuenta c1cuenta =c1.getAccountList().get(0);
        System.out.println(c1cuenta.getSaldo());
        c1cuenta.addMovement(new Movimiento(TipoMovimiento.INGRESO, c1cuenta, null, 100));
        System.out.println(c1cuenta.getSaldo());
        c1cuenta.addMovement(new Movimiento(TipoMovimiento.PAGO_RECIBO, c1cuenta, null, 100));
        System.out.println(c1cuenta.getSaldo());
    }

    private static void generateClients(){
        clientList.add(new Cliente("72845634B", Utils.hashFunc("12313"), "Alex Cortes"));
        clientList.add(new Cliente("16278200L", Utils.hashFunc("123456"), "Juan perez"));
        clientList.add(new Cliente("72735023F", Utils.hashFunc("654321"), "Antonio Gomez"));
        for (Cliente c: clientList) {
            c.createAccount(Cuenta.generateAccountNumber());
            c.createAccount(Cuenta.generateAccountNumber());
        }
    }

    public static Cliente loginCliente(String dni, String password) throws DatoNoValidoException {
        if (Validator.checkDni(dni)){
            Cliente c = findClienteByDni(dni);
            if (c != null && Utils.checkHash(password, c.getAccessPassword())) {
                return c;
            } else{
                throw new DatoNoValidoException("La contraseña introducida no es correcta");
            }
        } else {
            throw new DatoNoValidoException("El dni no es valido");
        }
    }


    private static Cliente findClienteByDni(String dni){
        Cliente cliente = null;
        for (Cliente c: clientList) {
            if (cliente == null && c.getDni().equalsIgnoreCase(dni))
                cliente = c;
        }
        return cliente;
    }

    private static Cliente findClienteByAccountNo(String accountNo){
        Cliente cliente = null;
        for (Cliente c: clientList) {
            if (cliente == null){
                for (Cuenta acc: c.getAccountList()){
                    if ( acc.getAccountNo().equalsIgnoreCase(accountNo))
                        cliente = c;
                }
            }
        }
        return cliente;
    }




}
