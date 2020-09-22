package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class TestingArea {
    public static void main(String[] args) throws TreeException {
        AvlTree<Integer, Integer> at = new AvlTree<>();

        Consumer<AvlLeaf<Integer, Integer>> consumer = new Consumer<AvlLeaf<Integer, Integer>>() {
            @Override
            public void accept(AvlLeaf<Integer, Integer> integerIntegerAvlLeaf) {
                System.out.println("Value: " + integerIntegerAvlLeaf.getValue() + "; balance: " + integerIntegerAvlLeaf.getBalance());
            }
        };
        int[] ints = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int i = 0; i < ints.length; i++) {
            at.add(new AvlLeaf<>(ints[i], ints[i]));
        }

        at.delete(1);
        at.process(consumer);
        System.out.println();
        at.delete(0);
        at.process(consumer);
        System.out.println();
        at.delete(2);
        at.process(consumer);
        System.out.println();
        at.delete(6);
        at.process(consumer);
        System.out.println();
        at.delete(4);
        at.process(consumer);
        System.out.println();
    }
}
