package ru.progwards.java1.lessons.arrays;

public class Eratosthenes {
    private boolean[] sieve;

    public Eratosthenes(int N) {
        sieve = new boolean[N];
        for (int i = 0; i < N; i++) {
            sieve[i] = true;
        }
        sift();
    }

    public boolean getSieveValue(int n) {
        return sieve[n];
    }

    private void sift() {
        for (int i = 2; i <= Math.sqrt(sieve.length); i++) {
            for (int j = i + i; j < sieve.length; j = j + i) {
                sieve[j] = false;
            }
        }
    }

    public boolean isSimple(int n) {
        Eratosthenes erat = new Eratosthenes(n + 1);
        return erat.getSieveValue(n);
    }

    public static void main(String[] args) {
        Eratosthenes erat = new Eratosthenes(1);
        System.out.println(erat.isSimple(83));
        System.out.println(erat.isSimple(99));
        System.out.println(erat.isSimple(1010101));
    }
}
