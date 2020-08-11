package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {

    public static double volumeBallDouble(double radius) {
        return 4 / 3 * Math.PI * Math.pow(radius, 3);
    }

    public static float volumeBallFloat(float radius) {
        return  4 / 3 * (float)Math.PI * (float)Math.pow(radius, 3);
    }

    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - (double)volumeBallFloat((float)radius);
    }
}
