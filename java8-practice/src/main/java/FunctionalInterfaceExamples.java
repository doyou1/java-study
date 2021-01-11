import java.util.function.Function;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {
        // 람다식 표현
        Function<String,Integer> toInt = (value) -> Integer.parseInt(value);

        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = Function.identity();

        System.out.println(identity.apply(000));
    }

}
