package com.imaleex.Models.Operations;

import com.imaleex.Models.Cuenta;

/**
 * @author Alex Cortes
 */
public class Movimiento {
    private TipoMovimiento type;
    private Cuenta cuentaDestino;
    private Cuenta cuentaOrigen;
    private double importe;

    public Movimiento(TipoMovimiento type, Cuenta cuentaDestino, Cuenta cuentaOrigen, double importe) {
        this.type = type;
        this.cuentaDestino = cuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
        this.importe = importe;
    }


    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public double getImporte() {
        return importe;
    }

    public String getType(){
        String movementType;
        switch (type){
            case TRANSFERENCIA:
                movementType = "Transferencia";
                break;
            case INGRESO:
                movementType = "Ingreso";
                break;
            case PAGO_RECIBO:
                movementType = "Pago de recibo";
                break;
            default:
                movementType = "Tipo Invalido";
        }
        return movementType;
    }


    public TipoMovimiento getEnumType(){
        return this.type;
    }



}
