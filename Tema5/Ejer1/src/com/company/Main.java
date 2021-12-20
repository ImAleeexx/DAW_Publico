package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

    /*
        VARIABLES GLOBALES
     */

    public static List<Double> integerList = new ArrayList<Double>();

    /*
        METODO PRINCIPAL
     */

    public static void main(String[] args) {
        startArrayListCreation();
    }



    /*
        METODOS GENERALES PARA PRODUCCION
     */

    public static void startArrayListCreation() {
        double i = -1;
        do {
            i = inputDouble("Introduce numero a añadir a la ArrayList");
            if (i >= 0)
                integerList.add(i);
        } while (i >= 0);
        if (integerList.toArray().length <= 0) {
            showErrorMessage("El array no puede estar vacia");
            startArrayListCreation();
        }
        showStartMenu();
    }

    public static void showStartMenu() {
        String menuInfo = "               MENU PRINCIPAL\n" +
                "Lista de opciones disponibles: \n" +
                "a) Maximo y minimo.\n" +
                "b) Buscar numero. \n" +
                "c) Buscar y borrar.\n" +
                "d) Convertir a array.\n" +
                "e) Mostrar n elementos.\n" +
                "f) Insertar numero final.\n" +
                "g) Insertar en posicion.\n" +
                "h) Borrar en posicion.\n" +
                "i) Calcular suma y media.\n" +
                "j) Finalizar\n\n";
        String menuInput = inputString(menuInfo).toLowerCase();
        parseMenuInput(menuInput.toCharArray()[0]);
    }

    public static void parseMenuInput(char c) {
        switch (c) {
            case 'a':
                maxAndMin();
                break;
            case 'b':
                findNumber();
                break;
            case 'c':
                findAndDeleteNumber();
                break;
            case 'd':
                convertToArray();
                break;
            case 'e':
                isEmpty();
                break;
            case 'f':
                addAtEnd();
                break;
            case 'g':
                addToIndex();
                break;
            case 'h':
                removeIndex();
                break;
            case 'i':
                calculateSumAndAvg();
                break;
            case 'j':
                System.exit(0);
                break;
            default:
                showErrorMessage("La opcion introducida no esta definida");
                showStartMenu();

                break;
        }
        showStartMenu();
    }


    /*
       MAXIMOS Y MINIMOS
    */
    public static void maxAndMin() {
        Double max = Collections.max(integerList);
        Double min = Collections.min(integerList);
        showInfoMessage("El numero mas alto es " + max + " y el mas bajo es " + min);
    }

     /*
        BUSCA NUMEROS
     */

    public static void findNumber(){
        int ocurrences = 0;
        double n = -1;
        while (n < 0)
            n = inputDouble("Introduce el numero a buscar");

        for (Double i: integerList) {
            if (i == n)
                ocurrences++;
        }

        if (ocurrences > 0)
            showInfoMessage("El numero " + n + " se ha encontrado " + ocurrences + " veces");
        else
            showInfoMessage("El numero no se encuentra en la array");

    }

    /*
        BUSCA BORRA
     */
    public static void findAndDeleteNumber(){
        int ocurrences = 0;
        double n = -1;
        while (n < 0)
            n = inputDouble("Introduce el numero a buscar");
        System.out.println(n);
        Iterator<Double> itr = integerList.iterator();
        while (itr.hasNext()){
            if (itr.next().equals(n)){
                itr.remove();
                ocurrences++;
            }
        }

        if (ocurrences > 0)
            showInfoMessage("El numero " + n + " se ha encontrado y eliminado " + ocurrences + " veces");
        else
            showInfoMessage("El numero no se encuentra en la array");

    }

    /*
    ESTA VACIO
    */
    public static void isEmpty(){
        if (!integerList.isEmpty())
            showInfoMessage("El numero de elementos en la ArrayList es de " + integerList.size());
        else
            showErrorMessage("La ArrayList esta vacia");
    }

    /*
    AÑADIR NUMERO AL FINAL
    */
    public static void addAtEnd(){
        double n = -1;
        while (n < 0)
            n = inputDouble("Introduce un numero a añadir al final de la array");
        integerList.add(integerList.size(), n);
    }


    /*
    AGREGAR NUMERO POR POSICION
    */
    public static void addToIndex(){
        int p = -1;
        double n = -1;
        while (p < 0)
            p = inputInt("Introduce la posicion del numero a añadir");
        if (p > integerList.size()){
            p = -1;
            showErrorMessage("La posicion introducida esta fuera de los limites de la ArrayList.\n" +
                    "La posicion  maxima es "+integerList.size());
        } else {
            while (n < 0)
                n = inputDouble("Introduce el numero a añadir");
            integerList.add(p,n);
        }

    }

    /*
    BORRAR NUMERO POR POSICION
    */
    public static void removeIndex(){
        int n = -1;
        while (n < 0)
            n = inputInt("Introduce la posicion del numero a borrar");
        if (n > (integerList.size()-1)){
            n = -1;
            showErrorMessage("La posicion introducida esta fuera de los limites de la ArrayList.\n" +
                    "La posicion  maxima es "+(integerList.size()-1));
        } else {
            integerList.remove(n);
        }

    }
    /*
    CALCULAR SUMA Y MEDIA
    */
    public static void calculateSumAndAvg(){
        int sum = 0;
        double avg = 0.0;
        for (Double d: integerList){
            sum += d;
        }
        avg = sum / integerList.size();
        showInfoMessage("Suma :" + sum + " Media: "+ avg);
    }


    public static Double[] convertToArray(){
        return (Double[]) integerList.toArray();
    }

     /*
        METODOS GENERALES PARA UTILIDADES DE MENSAJES
     */

    public static void showErrorMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox);
    }

    /*
        METODOS GENERALES PARA TRATAMIENTO DE VARIABLES
     */

    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }


    public static String inputString(String msgBox) {
        String var = JOptionPane.showInputDialog(msgBox);

        if (var == null || var.isEmpty()) {
            showErrorMessage("El dato introducido no es valido");
            var = inputString(msgBox);
        }

        return var;
    }

    public static int inputInt(String msgBox) {
        int var = -1;

        try {
            String s = JOptionPane.showInputDialog(msgBox);
            if (s.isEmpty()) {
                throw new NullPointerException();
            }
            var = Integer.parseInt(s);
            if (var < 0) {
                showErrorMessage("No se pueden introducir numeros negativos");
                var = inputInt(msgBox);
            }
        } catch (NullPointerException e) {
            return -1;
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
            e.printStackTrace();
            var = inputInt(msgBox);

        }
        return var;
    }

        public static double inputDouble(String msgBox) {
            double var = -1;

            try {
                String s = JOptionPane.showInputDialog(msgBox);
                if (s.isEmpty()) {
                    throw new NullPointerException();
                }
                var = Double.parseDouble(s);
                if (var < 0) {
                    showErrorMessage("No se pueden introducir numeros negativos");
                    var = inputDouble(msgBox);
                }
            } catch (NullPointerException e) {
                return -1;
            } catch (Exception e) {
                showErrorMessage("El dato introducido no es valido");
                e.printStackTrace();
                var = inputDouble(msgBox);

            }

        return var;
    }


}
