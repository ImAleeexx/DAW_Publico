package com.imaleex.Models;

import com.imaleex.Exceptions.DatoNoValidoException;
import com.imaleex.Models.Operations.Movimiento;
import com.imaleex.Utils.Utils;
import com.imaleex.Utils.Validator;

import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Cliente {
    private String dni;
    private String accessPassword;
    private String name;
    private ArrayList<Cuenta> accountList;

    public Cliente(String dni, String accessPassword, String name) {
        try{
            this.setDni(dni);
            this.accessPassword = accessPassword;
            this.name = name;
            this.accountList = new ArrayList<Cuenta>();
        } catch (DatoNoValidoException e){
            Utils.showErrorMessage(e.getMessage());
        }
    }

    public String getDni() {
        return dni;
    }

    public String getAccessPassword() {
        return accessPassword;
    }

    public void setAccessPassword(String accessPassword) {
        this.accessPassword = accessPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Cuenta> getAccountList() {
        return accountList;
    }
    public void createAccount(String accountNo) {
        accountList.add(new Cuenta(accountNo));
    }
    public void setAccountList(ArrayList<Cuenta> accountList) {
        this.accountList = accountList;
    }

    private void setDni(String s) throws DatoNoValidoException {
        if (Validator.checkDni(s)){
            this.dni = s;
        } else{
            throw new DatoNoValidoException("El DNI no es valido");
        }
    }
}
