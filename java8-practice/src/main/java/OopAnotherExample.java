
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
        final CalculatorService2 calculatorService2 = new CalculatorService2(new Addition());
        int additionResult = calculatorService2.calculate(1,1);
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

    public CalculatorService2(final Calculation calculation) {
        this.calculation = calculation;
    }

    public int calculate(int num1, int num2) {
        return calculation.calculate(num1,num2);
    }
}