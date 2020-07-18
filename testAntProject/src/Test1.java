import org.junit.Test;

import java.math.BigInteger;

import static junit.framework.TestCase.assertEquals;

public class Test1 {
    @Test
    public void test1() {
        test(1, BigInteger.ONE);
    }

    @Test
    public void test2() {
        test(2, BigInteger.ONE);
    }

    @Test
    public void test3() {
        test(55, BigInteger.valueOf(139583862445L));
    }

    private void test(int num, BigInteger val) { // метод по проверке правильности подсчётов
        BigInteger res = Main4Ant.fibonacci(num);
        assertEquals("False. Fibonacci (" + num + ") = " + val + ", not " + res, res, val); // сообщение, переданное первым параметром, выведется в случае ошибки
    }
}
