package ru.progwards.java2.lessons.trees;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class AvlBinaryTreeTest {
    private AvlBinaryTree<Integer, Integer> abt = new AvlBinaryTree<>();
    private AvlTreeLeaf<Integer, Integer> firstRoot = new AvlTreeLeaf<>(0, 0);

    @Test
    public void whenTryToAddValuesAtLeftAndRightThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(7, 7);
        abt.add(-5, -5);
        Assert.assertEquals(firstRoot.getLeft().getValue(), -5);
        Assert.assertEquals(firstRoot.getRight().getValue(), 7);
    }

    @Test
    public void whenTryToDeleteLeafThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(7, 7);
        abt.add(-5, -5);
        abt.delete(-5);
        abt.delete(7);
        Assert.assertNull(firstRoot.getRight());
        Assert.assertNull(firstRoot.getLeft());
    }

    @Test
    public void whenTryToMakeSmallRightRotationMinimumLeafsThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(-5, -5);
        abt.add(-7, -7);
        Assert.assertEquals(firstRoot.getParent().getValue(), -5);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), -7);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 0);
    }

    @Test
    public void whenTryToMakeSmallRightRotationWithNormalNumberOfLeafsThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(-5, -5);
        abt.add(7, 7);
        abt.add(-3, -3);
        abt.add(-7, -7);
        abt.add(-9, -9);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 0);
        Assert.assertEquals(firstRoot.getParent().getValue(), -5);
        Assert.assertEquals(firstRoot.getLeft().getValue(), -3);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), -7);
        Assert.assertEquals(firstRoot.getRight().getValue(), 7);
        Assert.assertEquals(firstRoot.getParent().getLeft().getLeft().getValue(), -9);
    }

    @Test
    public void whenTryToMakeSmallLeftRotationWithMinimumNumberOfLeafsThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(10, 10);
        abt.add(20, 20);
        Assert.assertEquals(firstRoot.getParent().getValue(), 10);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), 0);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 20);
    }

    @Test
    public void whenTryToMakeSmallLeftRotationWithNormalNumberOfLeafsThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(-5, -5);
        abt.add(7, 7);
        abt.add(5, 5);
        abt.add(9, 9);
        abt.add(11, 11);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), 0);
        Assert.assertEquals(firstRoot.getLeft().getValue(), -5);
        Assert.assertEquals(firstRoot.getRight().getValue(), 5);
        Assert.assertEquals(firstRoot.getParent().getValue(), 7);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 9);
        Assert.assertEquals(firstRoot.getParent().getRight().getRight().getValue(), 11);
    }

    @Test
    public void whenTryToMakeBigLeftRotationThenDoIt() throws TreeException {
        abt.add(firstRoot);
        abt.add(7, 7);
        abt.add(-5, -5);
        abt.add(5, 5);
        abt.add(9, 9);
        abt.add(3, 3);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), 0);
        Assert.assertEquals(firstRoot.getParent().getValue(), 5);
        Assert.assertEquals(firstRoot.getRight().getValue(), 3);
        Assert.assertEquals(firstRoot.getLeft().getValue(), -5);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 7);
        Assert.assertEquals(firstRoot.getParent().getRight().getRight().getValue(), 9);
    }

    @Test
    public void whenTryToMakeBigRightRotationThenDoIr() throws TreeException {
        abt.add(firstRoot);
        abt.add(-7, -7);
        abt.add(5, 5);
        abt.add(-5, -5);
        abt.add(-9, -9);
        abt.add(-3, -3);
        Assert.assertEquals(firstRoot.getParent().getRight().getValue(), 0);
        Assert.assertEquals(firstRoot.getParent().getValue(), -5);
        Assert.assertEquals(firstRoot.getLeft().getValue(), -3);
        Assert.assertEquals(firstRoot.getRight().getValue(), 5);
        Assert.assertEquals(firstRoot.getParent().getLeft().getValue(), -7);
        Assert.assertEquals(firstRoot.getParent().getLeft().getLeft().getValue(), -9);
    }

    @Test
    public void whenTryToMakeVeryDeepTreeThenLookOnRotations() throws TreeException {
        abt.add(firstRoot);
        for (int i = 1; i < 10000; i++) {
            abt.add(i, i);
        }
        ArrayList<Integer> values = new ArrayList<>();
        abt.process(y -> values.add(y.getValue()));
        Random r = new Random();
        Assert.assertTrue(values.contains(r.nextInt(10000)));
        Assert.assertTrue(values.contains(r.nextInt(10000)));
        Assert.assertTrue(values.contains(r.nextInt(10000)));
    }
}
