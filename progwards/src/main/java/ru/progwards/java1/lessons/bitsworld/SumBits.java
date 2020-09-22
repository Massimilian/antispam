package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBitsOrd(byte value) {
        int result = 0;
        String s = Integer.toBinaryString((int) value);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result++;
            }
        }
        return result;
    }


    public static int sumBits(byte value) {
        int result = 0;
        while (value != 0) {
            result += value&1;
            value = (byte) (value>>1);
        }
        return result;
    }

    public static void main(String[] args) {
        byte b = 75;
        System.out.println(Integer.toBinaryString((int) b));
        System.out.println(SumBits.sumBitsOrd(b));
        System.out.println(SumBits.sumBits(b));
    }
}
