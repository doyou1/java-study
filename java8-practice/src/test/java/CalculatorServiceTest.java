import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest {

//    @Test
//    public void testCalculateAddition() throws Exception {
//        CalculatorService calculatorService = new CalculatorService();
//
//        final int actual = calculatorService.calculate('+',1,1);
//
//        assertThat(actual).isEqualTo(2);
//    }
//
//    @Test
//    public void testCalculatesubtraction() throws Exception {
//        CalculatorService calculatorService = new CalculatorService();
//
//        final int actual = calculatorService.calculate('-',1,1);
//
//        assertThat(actual).isEqualTo(0);
//    }
//
//    @Test
//    public void testCalculateMultiple() throws Exception {
//        CalculatorService calculatorService = new CalculatorService();
//
//        final int actual = calculatorService.calculate('*',3,2);
//
//        assertThat(actual).isEqualTo(6);
//    }
//
//    @Test
//    public void testCalculateDivision() throws Exception {
//        CalculatorService calculatorService = new CalculatorService();
//
//        final int actual = calculatorService.calculate('/',8,4);
//
////        / by zero
////        java.lang.ArithmeticException: / by zero
////        at CalculatorService.calculate(OopAnotherExample.java:30)
////        at CalculatorServiceTest.testCalculateDivision(CalculatorServiceTest.java:39)
////        final int actual = calculatorService.calculate('/',8,0);
//
//        assertThat(actual).isEqualTo(2);
//    }

    @Test
    public void testCalculation() throws Exception {
        Calculation calculation = new Division();
        final int actual = calculation.calculate(4, 2);

        assertThat(actual).isEqualTo(2);
    }

}
