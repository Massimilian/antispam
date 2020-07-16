package ru.progwards.java2.lessons.tests.test.calc.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.SimpleCalculator;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SimpleCalculatorDivTest {
    private SimpleCalculator sc = new SimpleCalculator();

    @Parameterized.Parameter(0)
    public int val1;

    @Parameterized.Parameter(1)
    public int val2;

    @Parameterized.Parameter(2)
    public int result;

    @Parameterized.Parameters(name = "Test №{index}: {0} / {1} = {2}")
    public static Iterable<Object> forTest() {
        return Arrays.asList(new Object[][]{
                {6, 3, 2},
                {200, -200, -1},
                {0, Integer.MIN_VALUE, 0},
                {-9000, -1000, 9},
                {99999999, 3, 33333333}
        });
    }

    @Test
    public void WhenTryToSumValuesThenDoIt() {
        Assert.assertEquals(sc.div(val1, val2), result);
    }

}
