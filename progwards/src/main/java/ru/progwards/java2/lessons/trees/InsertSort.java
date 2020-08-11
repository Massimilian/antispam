package ru.progwards.java2.lessons.trees;

public class InsertSort {
    public int[] sort(int[] myArray) {
        int size = myArray.length - 1;
        for (int i = (myArray.length / 2); i >= 0; i--) {
            heapify(myArray, i, size);
        }
        for (int i = size; i >= 0; i--) {
            int temp = myArray[0];
            myArray[0] = myArray[size];
            myArray[size] = temp;
            size--;
            heapify(myArray, 0, size);
        }
        return myArray;
    }

    public void heapify(int[] myArray, int i, int heapSize) { // упорядочиваем пирамиду, если это необходимо
        int a = 2 * i;
        int b = 2 * i + 1;
        int largestElement;
        if (a <= heapSize && myArray[a] > myArray[i]) {
            largestElement = a;
        } else {
            largestElement = i;
        }
        if (b <= heapSize && myArray[b] > myArray[largestElement]) {
            largestElement = b;
        }
        if (largestElement != i) {
            int temp = myArray[i];
            myArray[i] = myArray[largestElement];
            myArray[largestElement] = temp;
            heapify(myArray, largestElement, heapSize);
        }
    }
}
