package ru.progwards.java2.lessons.recurtion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class AsNumbersSum {
    public static String asNumbersSum(int number) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(List.of(number)));
        asNumbersSum(list);
        return finalPrepare(list);
    }

    private static ArrayList<ArrayList<Integer>> asNumbersSum(ArrayList<ArrayList<Integer>> list) {
        ArrayList<Integer> last = list.get(list.size() - 1);
        ArrayList<Integer> newLast = (ArrayList<Integer>) last.clone();
        if (last.size() == 1 && last.get(0) != 1) {
            newLast.set(0, last.get(0) - 1);
            newLast.add(1);
            list.add(newLast);
            asNumbersSum(list);
            return list;
        }
        for (int i = last.size() - 1; i > 0; i--) {
            if (last.get(i) == 1 && last.get(i - 1) > 2) {
                newLast.set(i, last.get(i) + 1);
                newLast.set(i - 1, last.get(i - 1) - 1);
                list.add(newLast);
                asNumbersSum(list);
            }
            if (last.get(i) > 1) {
                newLast.set(i, last.get(i) - 1);
                newLast.add(1);
                list.add(newLast);
                asNumbersSum(list);
            }
        }
        if (list.get(list.size() - 1).get(0) == 2 && list.get(list.size() - 1).get(1) == 1) {
            newLast.set(0, 1);
            newLast.add(1);
            list.add(newLast);
        }
        return list;
    }

    private static String finalPrepare(ArrayList<ArrayList<Integer>> list) {
        StringBuilder finalResult = new StringBuilder();
        ArrayList<String> complex = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder result = new StringBuilder();
            result.append(list.get(i).get(0));
            for (int j = 1; j < list.get(i).size(); j++) {
                result.append(String.format("+%s", String.valueOf(list.get(i).get(j))));
            }
            complex.add(result.toString());
        }

        complex = (ArrayList<String>) complex.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < complex.size(); i++) {
            finalResult.append(complex.get(i));
            finalResult.append(" = ");
        }
        return finalResult.substring(0, finalResult.length() - 3);
    }
}
