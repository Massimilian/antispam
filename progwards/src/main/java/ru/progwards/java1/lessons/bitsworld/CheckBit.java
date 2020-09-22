package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber) {
        return ((byte) (value>>bitNumber))&1;
    }

    public static void main(String[] args) {
        byte b = -8;
        System.out.println(Integer.toBinaryString((int) b));
        for (int i = 31; i >= 0; i--) {
            System.out.print(CheckBit.checkBit(b, i));
        }
    }
}

