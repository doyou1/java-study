package youtube_kevin;

public class OopAndFpExamples {

    public static void main(String[] args) {
        final FpCalculatorService fpCalculatorService = new FpCalculatorService();
//        System.out.println("      addition: " + fpCalculatorService.calculate(new Addition(), 12, 2));
//        System.out.println("   subtraction: " + fpCalculatorService.calculate(new Subtraction(), 12, 2));
//        System.out.println("multiplication: " + fpCalculatorService.calculate(new Multiplication(), 12, 2));
//        System.out.println("      division: " + fpCalculatorService.calculate(new Division(), 12, 2));
        final Calculation addtion = (i1, i2) -> i1+i2;
        System.out.println("      addition: " + fpCalculatorService.calculate((i1, i2) -> i1+i2, 12, 2));
        System.out.println("      addition: " + fpCalculatorService.calculate(addtion, 12, 2));
        System.out.println("   subtraction: " + fpCalculatorService.calculate((i1, i2) -> i1-i2, 12, 2));
        System.out.println("multiplication: " + fpCalculatorService.calculate((i1, i2) -> i1*i2, 12, 2));
        System.out.println("      division: " + fpCalculatorService.calculate((i1, i2) -> i1/i2, 12, 2));
    }
}

class CalculatorService {
    private final Calculation calculation;
    private final Calculation calculation2;

    public CalculatorService(final Calculation calculation, final Calculation calculation2) {
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

class FpCalculatorService {
    public int calculate(Calculation calculation, int num1, int num2) {
        if(num1 > 0 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
        }
    }
}