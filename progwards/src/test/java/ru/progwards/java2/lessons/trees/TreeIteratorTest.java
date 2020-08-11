package ru.progwards.java2.lessons.trees;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeIteratorTest {
    BinaryTree<Integer, String> bt = new BinaryTree<>();

    @Test
    public void whenTryToUseAllIteratorsThenDoIt() throws TreeException {
        bt.add(1, "One");
        bt.add(4, "Four");
        bt.add(2, "Two");
        bt.add(3, "Three");
        bt.add(6, "Six");
        bt.add(7, "Seven");
        bt.add(5, "Five");
        ArrayList<TreeLeaf<Integer, String>> list = new ArrayList<>();
        Iterator<TreeLeaf<Integer, String>> iterator = bt.getIterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        ArrayList<TreeLeaf<Integer, String>> cons = new ArrayList<>();
        iterator = bt.getIteratorCons();
        while (iterator.hasNext()) {
            cons.add(iterator.next());
        }
        ArrayList<TreeLeaf<Integer, String>> rec = new ArrayList<>();
        iterator = bt.getIteratorRec();
        while (iterator.hasNext()) {
            rec.add(iterator.next());
        }
        Assert.assertEquals(list, cons);
        Assert.assertEquals(cons, rec);
        Assert.assertEquals(rec, list);
    }
}
