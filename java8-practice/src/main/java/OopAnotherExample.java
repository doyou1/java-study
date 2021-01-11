
public class OopAnotherExample {

    public static void main(String[] args) {
//        final CalculatorService calculatorService = new CalculatorService();
//        final int additionResult = calculatorService.calculate('+',1, 1);
//        System.out.println(additionResult);
//
//        final int subtractionResult = calculatorService.calculate('-',1,1);
//        System.out.println(subtractionResult);
//
//        final int multiplicationResult = calculatorService.calculate('*',1,1);
//        System.out.println(multiplicationResult);
//
//        final int divisionResult = calculatorService.calculate('/',8,4);
//        System.out.println(divisionResult);

//      스트래티지 패턴
        final CalculatorService2 calculatorService2 = new CalculatorService2(new Addition(), new Addition());
        int additionResult = calculatorService2.calculate(1, 1);
        System.out.println(additionResult);
    }
}

//class CalculatorService {
//    public int calculate(char calculation, int num1, int num2) {
//        if(calculation == '+') {
//            return num1 + num2;
//        }else if(calculation == '-') {
//            return num1 - num2;
//        }else if(calculation == '*') {
//            return num1 * num2;
//        }else if(calculation == '/') {
//            return num1 / num2;
//        }else{
//            throw new IllegalStateException("Unknown calculation: " + calculation);
//        }
//    }
//
//}

    interface Calculation {
        int calculate(int num1, int num2);
    }

    class Addition implements Calculation {
        @Override
        public int calculate(int num1, int num2) {
            return num1 + num2;
        }
    }

    class Subtraction implements Calculation {
        @Override
        public int calculate(int num1, int num2) {
            return num1 - num2;
        }
    }

    class Multiplication implements Calculation {
        @Override
        public int calculate(int num1, int num2) {
            return num1 * num2;
        }
    }

    class Division implements Calculation {
        @Override
        public int calculate(int num1, int num2) {
            return num1 / num2;
        }
    }

    class CalculatorService2 {
        private final Calculation calculation;
        private final Calculation calculation2;

        public CalculatorService2(final Calculation calculation, final Calculation calculation2) {
            this.calculation = calculation;
            this.calculation2 = calculation2;
        }

        public int calculate(int num1, int num2) {
            if (num1 > 10 && num2 < num1) {
                return calculation.calculate(num1, num2);
            } else {
                throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
            }
        }

        public int compute(int num1, int num2) {
            if (num1 > 10 && num2 < num1) {
                return calculation2.calculate(num1, num2);
            } else {
                throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
            }
        }
    }
