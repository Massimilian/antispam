package ru.progwards.java2.lessons.trees;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class BinaryTreeAndAvlBinaryTreeSpeedTest {
    StringBuilder sb = new StringBuilder();
    int size = 10000;
    BinaryTree<Integer, Integer> bt = new BinaryTree<>();
    AvlBinaryTree<Integer, Integer> abt = new AvlBinaryTree<>();
    ArrayList<Integer> list = this.prepare();
    Instant start;
    Instant end;
    Duration dur;


    @Test
    public void whenTryTheSpeedsOfAddindDeletingAndFindingOrderedValuesThenGetSpeedResultOfBinaryTree() throws TreeException {
        sb.append(String.format("BinaryTree results with %d elements.%s ========== %s", size, System.lineSeparator(), System.lineSeparator()));
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>(i, list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Ordered adding result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        start = Instant.now();
        for (int i = size - 1; i >= 0; i--) {
            bt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Deleting result (most long situation): %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Deleting result (most quick situation): %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        mix();
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>((int)list.get(i), list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Unordered adding result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.delete(i);
            if (i == 2049) {
                System.out.println("Stop");
            }
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Unordered deleting result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));
        System.out.println();
        System.out.println(sb.toString());
    }

    @Test
    public void whenTryTheSpeedOfAddindDeletingAndFindingOrderedValuesThenGetSpeedResultOfAvlBinaryTree() throws TreeException {
        sb.append(String.format("BinaryTree results with %d elements.%s ========== %s", size, System.lineSeparator(), System.lineSeparator()));
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>(i, list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Ordered adding result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        start = Instant.now();
        for (int i = size - 1; i >= 0; i--) {
            abt.delete(i);
            System.out.println(i);
            if (i == 2049) {
                System.out.println();
            }
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Deleting result (most long situation): %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Deleting result (most quick situation): %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        mix();
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>((int)list.get(i), list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Unordered adding result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));

        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        sb.append(String.format("Unordered deleting result: %d.%d seconds.%s", dur.getSeconds(), dur.getNano(), System.lineSeparator()));
        System.out.println();
        System.out.println(sb.toString());
    }

    @Test
    public void testingArea() throws TreeException {
        ArrayList<Integer> ints = new ArrayList<>();
        AvlBinaryTree<Integer, Integer> abt2 = new AvlBinaryTree<>();
        for (int i = 0; i < size; i++) {
            ints.add(i);
        }
        for (int i = 0; i < size; i++) {
            abt2.add(new AvlTreeLeaf<Integer, Integer>(i, i));
        }
        System.out.println();
    }

    @Test
    public void whenTryTheSpeedOfAddindNewUnorderedValuesThenGetSpeedResult() throws TreeException {
        mix();
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>(i, list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        String s = "BinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>(i, list.get(i)));
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        s = "AvlBinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
    }

    @Test
    public void whenTryTheSpeedOfDeletingOrderedValuesThenGetSpeedResult() throws TreeException {
        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        String s = "BinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        s = "AvlBinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
    }


    @Test
    public void whenTryTheSpeedOfDeletingUnorderedValuesThenGetSpeedResult() throws TreeException {
        mix();
        for (int i = 0; i < size; i++) {
            bt.add(new TreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            bt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        String s = "BinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
        for (int i = 0; i < size; i++) {
            abt.add(new AvlTreeLeaf<>(i, list.get(i)));
        }
        start = Instant.now();
        for (int i = 0; i < size; i++) {
            abt.delete(i);
        }
        end = Instant.now();
        dur = Duration.between(start, end);
        s = "AvlBinaryTree result: " + dur.getSeconds() + "." + dur.getNano() + " seconds.";
        System.out.println(s);
    }

    private ArrayList<Integer> prepare() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    private void mix() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(list.remove(random.nextInt(size - 1)));
        }
    }
}
