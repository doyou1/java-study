import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples2 {
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();

        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        final List<Integer> result = new ArrayList<>();
        Integer result = null;
        for(final Integer number : numbers) {
            if(number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if(newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result: " + result);

        // Prevent Nullpointerexception
        System.out.println(
                "Functional Result: " +
                numbers.stream()
                        .filter(number -> number > 3)
                        .filter(number -> number < 9)
                        .map(number -> number * 2)
                        .filter(number -> number >10)
                        .findFirst()
        );
        Optional<Integer> result1 =                 numbers.stream()
                .filter(number -> number > 3)
                .filter(number -> number < 9)
                .map(number -> number * 2)
                .filter(number -> number >10)
                .findFirst();
        Optional<Integer> result2 =                 numbers.stream()
                .filter(number -> number > 3)
                .filter(number -> number < 9)
                .map(number -> number * 2)
                .filter(number -> number > 20)
                .findFirst();

        System.out.println(
                result1.orElseThrow(() -> new NoSuchElementException())
        );
        System.out.println(
                "Optional.ofNullable(result1): " +
                Optional.ofNullable(result1.get())
        );
        final List<Integer> greaterThan3 = filter(numbers, i->i>3);
        final List<Integer> lessThan9 = filter(greaterThan3, i->i<9);
        final List<Integer> doubled = map(lessThan9, i -> i * 2);
        final List<Integer> greaterThan10 = filter(doubled, i->i>10);
        System.out.println(greaterThan10);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for(final T t : list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T,R> List<R> map(List<T> list, Function<T,R> mapper) {
        final List<R> result = new ArrayList<>();
        for(final T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}

