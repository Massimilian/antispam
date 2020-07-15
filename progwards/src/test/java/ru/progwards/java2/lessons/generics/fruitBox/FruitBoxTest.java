package ru.progwards.java2.lessons.generics.fruitBox;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FruitBoxTest {
    FruitBox fb = new FruitBox();
    FruitBox fbTwo = new FruitBox();
    ArrayList<Apple> apples = new ArrayList<>(List.of(new Apple(1f), new Apple(2f), new Apple(3f)));
    ArrayList<Orange> oranges = new ArrayList<>(List.of(new Orange(2f), new Orange(2f), new Orange(3f)));

    @Test
    public void WhenTryToAddApplesThenDoIt() {
        fb.add(apples);
        Assert.assertEquals(fb.add(apples), true);
        Assert.assertEquals(fb.add(oranges), false);
    }

    @Test
    public void WhenTryToCountFruitsWeightInBoxThenReturnValue() {
        fb.add(oranges);
        fb.add(oranges);
        Assert.assertEquals(fb.getWeight() == 14f, true);
    }

    @Test
    public void WhenTryToCountFruitsInBoxThenReturnCount() {
        fb.add(apples);
        fb.add(apples);
        Assert.assertEquals(fb.getCount() == 6, true);
    }

    @Test
    public void WhenTryToUniqueEqualsFruitsFromBoxToBoxThenDoIt() {
        fb.add(apples);
        fbTwo.add(apples);
        fb.moveTo(fbTwo);
        Assert.assertEquals(fb.getCount() == 0, true);
        Assert.assertEquals(fbTwo.getCount() == 6, true);
    }

    @Test
    public void WhenTryToUniqueNotEqualsFruitsFromBoxToBoxThenDoNotDoIt() {
        fb.add(apples);
        fbTwo.add(oranges);
        fbTwo.moveTo(fb);
        Assert.assertEquals(fb.getCount() == 3, true);
        Assert.assertEquals(fbTwo.getCount() == 3, true);
    }
}
