package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static int reverseDigits(int number) {
        String[] nums = String.valueOf(number).split("");
        String result = "";
        for (int i = nums.length - 1; i >= 0; i--) {
            result += nums[i];
        }
        return Integer.valueOf(result);
    }
}
