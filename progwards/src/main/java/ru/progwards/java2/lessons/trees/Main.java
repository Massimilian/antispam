package ru.progwards.java2.lessons.trees;

public class Main {
    public static void main(String[] args) throws TreeException {
        AvlBinaryTree<Integer, Integer> bt = new AvlBinaryTree<>();
        bt.add(0, 0);
        bt.add(7, 7);
        bt.add(-5, -5);
        bt.add(-7, -7);
        bt.add(-3, -3);
        bt.add(-9, -9);
        System.out.println();
    }
}
