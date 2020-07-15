package ru.progwards.java2.lessons.generics.dynamicArray;

import org.junit.Assert;
import org.junit.Test;

public class DynamicArrayTest {
    private DynamicArray<Integer> dai = new DynamicArray();
    private DynamicArray<String> das = new DynamicArray();

    @Test
    public void WhenTryToAddNewIntValuesThenDoIt() {
        dai.add(0);
        dai.add(1);
        Assert.assertEquals(dai.getCount(), 2);
    }

    @Test
    public void WhenTryToAddNewStringsValuesThenDoIt() {
        das.add("0");
        das.add("one");
        das.add("two");
        Assert.assertEquals(das.getCount(), 3);
    }

    @Test
    public void WhenTryToInsertThenGetNewIntValues() {
        dai.add(1);
        dai.add(2);
        dai.insert(0, 0);
        Assert.assertEquals(dai.get(0) == 0, true);
        Assert.assertEquals(dai.get(1) == 1, true);
        Assert.assertEquals(dai.get(2) == 2, true);
        Assert.assertEquals(dai.get(3) == null, true);
    }

    @Test
    public void WhenTryToRemoveThenCheckStringValues() {
        das.add("one");
        das.add("two");
        das.insert("0", 0);
        Assert.assertEquals(das.get(0).equals("0"), true);
        das.remove(0);
        Assert.assertEquals(das.get(0) == null, true);
    }

    @Test
    public void WhenTryToRemoveIntZeroTheCheckThatIsNotZeroNow() {
        dai.add(0);
        Assert.assertEquals(dai.get(0) == 0, true);
        Assert.assertEquals(dai.get(0) == null, false);
        dai.remove(0);
        Assert.assertEquals(dai.get(0) == null, true);
    }

    @Test
    public void WhenTryToGetRealSizeThenDoIt() {
        das.add("s");
        das.add("i");
        das.add("z");
        das.add("e");
        das.add("!");
        Assert.assertEquals(das.getSize(), 8);
    }
}