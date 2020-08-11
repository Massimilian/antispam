package ru.progwards.java1.basics;

import org.junit.Assert;
import org.junit.Test;
import ru.progwards.java1.lessons.basics.ReverseDigits;

public class ReverseDigitsTest {
    @Test
    public void whenTryToReverseSomeNumbersThenDoIt() {
        Assert.assertEquals(ReverseDigits.reverseDigits(123456789), 987654321);
    }
}
