package ru.progwards.java1.lessons.trianglefibo;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c) {
        return a + b > c || a + c > b || b + c > a && (a > 0 && b > 0 && c > 0);
    }

    public static boolean isRightTriangle(int a, int b, int c) {
        return isRight(a, b, c) || isRight(a, c, b) || isRight(b, c, a);
    }

    private static boolean isRight(int a, int b, int c) {
        return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2);
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || b == c || a == c;
    }
}
