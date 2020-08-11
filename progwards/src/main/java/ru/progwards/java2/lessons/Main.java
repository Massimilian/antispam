package ru.progwards.java2.lessons;

import ru.progwards.java2.lessons.trees.InsertSort;

public class Main {
    public static void main(String[] args) {
        InsertSort is= new InsertSort();
        int[] sorted = is.sort(new int[]{6, 0, 5, 3, 2, 1, 4});
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
    }
}
