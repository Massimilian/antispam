package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class TestingArea {
    public static void main(String[] args) throws TreeException {
        AvlBinaryTree<Integer, Integer> abt = new AvlBinaryTree<>();
        Consumer<AvlTreeLeaf<Integer, Integer>> consumer = new Consumer<>() {
            @Override
            public void accept(AvlTreeLeaf<Integer, Integer> integerIntegerAvlTreeLeaf) {
                System.out.println(integerIntegerAvlTreeLeaf.getValue() + " - " + integerIntegerAvlTreeLeaf.getBalance());
            }
        };
        for (int i = 0; i < 27; i++) {
            abt.add(i, i);
        }
        abt.process(consumer);
        System.out.println();
        for (int i = 0; i < 27; i++) {
            abt.delete(i);
            abt.process(consumer);
            System.out.println();
        }
        System.out.println();
   }

}
