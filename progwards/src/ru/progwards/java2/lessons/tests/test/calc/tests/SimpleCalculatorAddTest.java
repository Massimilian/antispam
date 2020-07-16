package ru.progwards.java2.lessons.tests.test.calc.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.SimpleCalculator;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SimpleCalculatorAddTest {
    private SimpleCalculator sc = new SimpleCalculator();

    @Parameterized.Parameter(0)
    public int val1;

    @Parameterized.Parameter(1)
    public int val2;

    @Parameterized.Parameter(2)
    public int result;

    @Parameterized.Parameters(name = "Test №{index}: {0} + {1} = {2}")
    public static Iterable<Object> forTest() {
        return Arrays.asList(new Object[][]{
                {1, 3, 4},
                {200, -200, 0},
                {0, 0, 0},
                {-1000, -9000, -10000},
                {12345678, 87654321, 99999999}
        });
    }

    @Test
    public void WhenTryToSumValuesThenDoIt() {
        Assert.assertEquals(sc.sum(val1, val2), result);
    }
}
