package com.company;

public class Sphere {
    double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double calcLength() {
        return 2 * Math.PI * this.radius;
    }

    public double calcCircumferenceArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double calcSphereArea() {
        return (4 * Math.PI * Math.pow(this.radius, 3)) / 3;
    }
}

