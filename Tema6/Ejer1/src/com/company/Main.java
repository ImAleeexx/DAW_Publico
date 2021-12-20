package com.company;



public class Main {

    public static void main(String[] args) {
        double radius = 0;
        do {
                System.out.print("Radio: ");
                radius = Utils.inputDouble("Introduce el radio");
        } while (radius <= 0);

        Sphere sphere = new Sphere(radius);
        StringBuilder sb = new StringBuilder();
        sb.append("Longitud: " + sphere.calcLength());
        sb.append("\nArea circunferencia: " + sphere.calcCircumferenceArea());
        sb.append("\nArea esfera: " + sphere.calcSphereArea());
        Utils.showInfoMessage(sb.toString());
    }
}
