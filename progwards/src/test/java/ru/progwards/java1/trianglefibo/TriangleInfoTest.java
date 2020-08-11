package ru.progwards.java1.trianglefibo;

import org.junit.Assert;
import org.junit.Test;
import ru.progwards.java1.lessons.trianglefibo.CyclesGoldenFibo;
import ru.progwards.java1.lessons.trianglefibo.TriangleInfo;
import ru.progwards.java1.lessons.trianglefibo.TriangleSimpleInfo;

public class TriangleInfoTest {
    @Test
    public void tryAllMethodsOfTriangleSimpleInfo() {
        Assert.assertEquals(TriangleSimpleInfo.maxSide(2,3, 4), 4);
        Assert.assertEquals(TriangleSimpleInfo.minSide(2, 3, 4), 2);
        Assert.assertTrue(TriangleSimpleInfo.isEquilateralTriangle(2, 2, 3));
        Assert.assertFalse(TriangleSimpleInfo.isEquilateralTriangle(2, 3, 4));
    }

    @Test
    public void tryAllMethodsOfTriangleInfo() {
        Assert.assertTrue(TriangleInfo.isTriangle(3, 3, 3));
        Assert.assertFalse(TriangleInfo.isTriangle(0, 0, 0));
        Assert.assertTrue(TriangleInfo.isRightTriangle(5, 4, 3));
        Assert.assertFalse(TriangleInfo.isRightTriangle(2, 2, 3));
        Assert.assertTrue(TriangleInfo.isIsoscelesTriangle(5, 5, 6));
        Assert.assertTrue(TriangleInfo.isIsoscelesTriangle(4, 7, 4));
        Assert.assertFalse(TriangleInfo.isIsoscelesTriangle(3, 5, 2));
    }
}
