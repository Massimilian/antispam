package ru.progwards.java1.lessons.trianglefibo;

public class TriangleSimpleInfo {
    public static int maxSide(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public static int minSide(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static boolean isEquilateralTriangle(int a, int b, int c) {
        return a == b || b == c || a ==c;
    }
}
