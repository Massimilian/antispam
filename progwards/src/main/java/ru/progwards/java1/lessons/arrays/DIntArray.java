package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] array = new int[0];

    public void add(int num) {
        int[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = num;
        array = newArray;
    }

    public void atInsert(int pos, int num) {
        if (pos > array.length) {
            System.out.println("Impossible operation!");
        } else if (pos == array.length) {
            this.add(num);
        } else {
            int[] leftPart = Arrays.copyOfRange(array, 0, pos);
            int[] rightPart = Arrays.copyOfRange(array, pos, array.length);
            int[] result = new int[array.length + 1];
            System.arraycopy(leftPart, 0, result, 0, leftPart.length);
            result[leftPart.length] = num;
            System.arraycopy(rightPart, 0, result, leftPart.length + 1, rightPart.length);
            this.array = result;
        }
    }

    public void atDelete(int pos) {
        if (pos > array.length - 1) {
            System.out.println("Impossible operation!");
        } else {
            int[] result = new int[array.length - 1];
            int[] leftPart = Arrays.copyOfRange(array, 0, pos);
            int[] rightPart = Arrays.copyOfRange(array, pos + 1, array.length);
            System.arraycopy(leftPart, 0, result, 0, leftPart.length);
            System.arraycopy(rightPart, 0, result, leftPart.length, rightPart.length);
            this.array = result;
        }
    }

    public int at(int pos) {
        if (pos > array.length - 1) {
            throw new UnsupportedOperationException("Impossible operation!");
        }
        return array[pos];
    }
}
