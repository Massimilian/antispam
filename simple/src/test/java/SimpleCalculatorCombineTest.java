
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SimpleCalculatorCombineTest {
    private static final SimpleCalculator sc = new SimpleCalculator();

    public static class SimpleCalculatorExceptionsTest {

        @Test(expected = ArithmeticException.class)
        public void WhenTryToPutIncompatibleSums() {
            sc.sum(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        @Test(expected = ArithmeticException.class)
        public void WhenTryToPutIncompatibleMults() {
            sc.mult(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        @Test(expected = ArithmeticException.class)
        public void WhenTryToPutZeroIntoDivs() {
            sc.div(Integer.MAX_VALUE, 0);
        }

        @Test(expected = ArithmeticException.class)
        public void WhenTryToPutIncompatibleDiffs() {
            sc.diff(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        @Test(expected = ArithmeticException.class)
        public void WhenTryToPutZeroIntoSpecDivs() {
            sc.correctDiv(Integer.MAX_VALUE, 0);
        }
    }

    public static class SimpleCalculatorSpecDivTest {
        @Test
        public void WhenTryToPutValuesIntoSpecDivThenDoItWell() {
            Assert.assertEquals(0.5, sc.correctDiv(2, 4), 0.0);
            Assert.assertEquals(0.075, sc.correctDiv(300, 4000), 0.0);
            Assert.assertEquals(2.0, sc.correctDiv(4, 2), 0.0);
        }
    }
}
