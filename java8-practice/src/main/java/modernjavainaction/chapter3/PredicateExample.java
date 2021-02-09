package modernjavainaction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * java.util.function.Predicate<T> 인터페이스는 test라는 추상 메서드를 정의하며 test는 제네릭 형식 T의 객체를 인수로 받아 불리언을 반환한다.
 */
public class PredicateExample {

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(Arrays.asList("1","2","3","","asd"), nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }
    static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for(T t : list) {
            if(p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }
}

@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

