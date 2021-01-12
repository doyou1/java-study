import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {
        // 람다식 표현
        Function<String,Integer> toInt = (value) -> Integer.parseInt(value);

        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = Function.identity();

        System.out.println(identity.apply(000));

        final Consumer<String> print = value -> System.out.println(value);
//      Bad return type in lambda expression: void cannot be converted to Void
//        final Function<String,Void> print2 = value -> System.out.println(value);
        final Consumer<String> print2 = value -> System.out.println("Hello " + value);
        print.accept("Hello");
        print2.accept("jh");
        print2.accept("chu");

        final Predicate<Integer> isPositive = i -> i > 0;
        System.out.println(isPositive.test(1));  //true
        System.out.println(isPositive.test(0));  //false
        System.out.println(isPositive.test(-1)); //false

        List<Integer> numbers = Arrays.asList(-5,-4,-3,-2,-1,0,1,2,3,4,5);
        List<Integer> positiveNumbers = new ArrayList<>();
        for(Integer num : numbers) {
            if(isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positive numbers: " + positiveNumbers);

        Predicate<Integer> lessThan3 = i -> i<3;
        List<Integer> numbersLessThan3 = new ArrayList<>();
        for(Integer num : numbers) {
            if(lessThan3.test(num)) {
                numbersLessThan3.add(num);
            }
        }
        System.out.println("lessthan3 numbers: " + numbersLessThan3);

        System.out.println("positive numbers: " + filter(numbers,isPositive));
        System.out.println("lessthan3 numbers: " + filter(numbers,lessThan3));

        final Supplier<String> supplier = () -> "hello";

        System.out.println(supplier.get() + "world");

        printIfValidIndex(0,"jh");
        printIfValidIndex(1,"jh");
        printIfValidIndex(-1,"jh");

        long start = System.currentTimeMillis();
        printIfValidIndex(0,getVeryExpensiveValue());
        printIfValidIndex(-2,getVeryExpensiveValue());
        printIfValidIndex(-1,getVeryExpensiveValue());
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start)/1000 + "seconds");

        start = System.currentTimeMillis();
        printIfValidIndexBySupplier(0,()->getVeryExpensiveValue());
        printIfValidIndexBySupplier(-2,()->getVeryExpensiveValue());
        printIfValidIndexBySupplier(-1,()->getVeryExpensiveValue());
        end = System.currentTimeMillis();
        System.out.println("It took " + (end - start)/1000 + "seconds");

    }

    private static String getVeryExpensiveValue(){
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Kevin";
    }

    private static void printIfValidIndex(int number, String value) {
        if(number >= 0) {
            System.out.println("The value is " + value + ".");
        }else{
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndexBySupplier(int number, Supplier<String> valueSupplier) {
        if(number >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        }else{
            System.out.println("Invalid");
        }
    }
    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for(T input : list) {
            if(filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

}
