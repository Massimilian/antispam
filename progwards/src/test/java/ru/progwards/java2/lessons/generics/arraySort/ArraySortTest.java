package ru.progwards.java2.lessons.generics.arraySort;

import org.junit.Assert;
import org.junit.Test;

public class ArraySortTest {
    private ArraySort as = new ArraySort();
    private Integer[] intResult = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String[] stringResult = {"action", "bed", "could", "door", "examen", "fork", "guest"};

    @Test
    public void WhenTryToSortIntsThenDoIt() {
        Integer[] ints = as.sort(4, 2, 5, 3, 6, 8, 7, 9, 1, 0);
        Assert.assertArrayEquals(ints, intResult);
    }

    @Test
    public void WhenTryToSortStringsThenDoIt() {
        String[] strings = as.sort("bed", "door", "could", "action", "fork", "examen", "guest");
        Assert.assertArrayEquals(strings, stringResult);
    }
}
