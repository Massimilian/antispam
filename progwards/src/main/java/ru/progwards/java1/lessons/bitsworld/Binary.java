package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    private final byte b;

    public Binary(byte num) {
        this.b = num;
    }

    public String toStringHard() {
        String result = Integer.toBinaryString((int) b);
        while (result.length() < 8) {
            result = "0" + result;
        }
        if (result.length() > 8) {
            result = result.substring(result.length() - 8);
        }
        System.out.println(result);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        byte changed = b;
        for (int i = 0; i < 8; i++) {
            sb.append(CheckBit.checkBit(changed, i));
        }
        return sb.reverse().toString();
    }

}
