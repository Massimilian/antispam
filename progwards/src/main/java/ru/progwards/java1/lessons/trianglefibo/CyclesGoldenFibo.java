package ru.progwards.java1.lessons.trianglefibo;

import java.util.ArrayList;

public class CyclesGoldenFibo {
    public static boolean containsDigit(int number, int digit) {
        return String.valueOf(number).contains(String.valueOf(digit));
    }

    public static int fiboNumber(int n) {
        int result;
        if (n == 0) {
            result = 0;
        } else {
            result = 1;
            int lastResult = result;
            int lastLastResult = result;
            if (n > 2) {
                for (int i = 3; i <= n; i++) {
                    result = lastResult + lastLastResult;
                    lastLastResult = lastResult;
                    lastResult = result;
                }
            }
        }
        return result;
    }

    public static boolean isGoldenTriangle(int a, int b, int c) {
        return TriangleInfo.isIsoscelesTriangle(a, b, c) && (isCorrectRelationship(a, b) || isCorrectRelationship(a, c) || isCorrectRelationship(b, c));
    }

    private static boolean isCorrectRelationship(double one, double two) {
        return one/two >= 1.61703 && one/two <= 1.61903 || two/one >= 1.61703 && two/one <= 1.61903;
    }

    public static void main(String[] args) {
        ArrayList<Integer> fibos = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            fibos.add(fiboNumber(i));
        }
        int count = 0;
        for (int i = 1; i < 15; i++) {
            if (isGoldenTriangle(fibos.get(i-1), fibos.get(i-1), fibos.get(i))) {
                System.out.printf("Triangle with sides %d, %d and %d is a Golden Triangle.%s", fibos.get(i-1), fibos.get(i-1), fibos.get(i), System.lineSeparator());
                count++;
            }
        }
        System.out.printf("There are %d Golden Triangles.", count);
    }
}
