package ru.progwards.java1.basics;

import org.junit.Test;
import ru.progwards.java1.lessons.basics.AccuracyDoubleFloat;

public class AccuracyDoubleFloatTest {
    @Test
    public void whenTryToLookAtDifferenceBetweenFloatAndDoubleThenDoIt() {
        System.out.println(AccuracyDoubleFloat.calculateAccuracy(1));
    }
}
