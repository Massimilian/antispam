import java.math.BigInteger;


public class Main4Ant {
    public static BigInteger fibonacci(int pos) {
        BigInteger prev = BigInteger.ONE;
        BigInteger fibo = BigInteger.ZERO;
        for (int i = 0; i < pos; i++) {
            BigInteger tmp = prev;
            prev = fibo;
            fibo = fibo.add(tmp);
        }
        return fibo;
    }

    public static void main(String[] args) {
        System.out.println("The 55th number of Fibonacchi is " + fibonacci(55));
    }
}
