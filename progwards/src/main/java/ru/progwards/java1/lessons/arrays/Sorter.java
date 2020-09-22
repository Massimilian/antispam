package ru.progwards.java1.lessons.arrays;

public class Sorter {
    public static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    a[i] += a[j];
                    a[j] = a[i] - a[j];
                    a[i] = a[i] - a[j];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 7, 8, 6, 5, 3, 4, 2, 1, 0};
        Sorter.sort(a);
        System.out.println();
    }
}
