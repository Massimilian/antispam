package ru.progwards.java1.trianglefibo;

import org.junit.Assert;
import org.junit.Test;
import ru.progwards.java1.lessons.trianglefibo.CyclesGoldenFibo;

public class CyclesGoldenFiboTest {
    @Test
    public void whenTryAllMethodsThenReturnCorrectResults() {
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 1));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 2));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 3));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 4));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 5));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 6));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 7));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 8));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 9));
        Assert.assertTrue(CyclesGoldenFibo.containsDigit(1234567890, 0));
        Assert.assertFalse(CyclesGoldenFibo.containsDigit(1234567890, 10));
        Assert.assertEquals(CyclesGoldenFibo.fiboNumber(3), 2);
        Assert.assertEquals(CyclesGoldenFibo.fiboNumber(10), 55);
    }
}
