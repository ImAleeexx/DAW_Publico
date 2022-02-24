package com.imaleex.Models;

import com.imaleex.Exceptions.OperationException;
import com.imaleex.Models.Operations.Movimiento;
import com.imaleex.Utils.Utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Alex Cortes
 */
public class Cuenta {
    private String accountNo;
    private ArrayList<Movimiento> movementList;

    public Cuenta(String accountNo) {
        this.accountNo = accountNo;
        this.movementList = new ArrayList<Movimiento>();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public ArrayList<Movimiento> getMovementList() {
        return movementList;
    }
    public boolean addMovement(Movimiento m){
        return movementList.add(m);
    }
    public double getSaldo(){
        double  currentSaldo = 0;
        for (Movimiento m: movementList){
            try{
                currentSaldo += resolveMovement(m);
            } catch (OperationException e){
                Utils.showErrorMessage(e.getMessage());
            }
        }
        return currentSaldo;
    }
    private double resolveMovement(Movimiento m ) throws OperationException {
        if (m.getCuentaDestino().equals(this)){
            switch (m.getEnumType()){
                case TRANSFERENCIA:
                case INGRESO:
                    return m.getImporte();
                case PAGO_RECIBO:
                    return m.getImporte() * -1;
            }
        } else if (m.getCuentaOrigen().equals(this)){
            switch (m.getEnumType()){
                case TRANSFERENCIA:
                case INGRESO:
                    return m.getImporte() * -1;
                case PAGO_RECIBO:
                    return m.getImporte();
            }
        }
        throw new OperationException("El sistema no reconoce ese tipo de movimiento");
    }
    public static String generateAccountNumber(){
        Random rand = new Random();
        String accountNo = "";
        for (int i = 0; i < 10; i++){
            accountNo += String.valueOf(rand.nextInt(10));
        }

        return accountNo;
    }
}
