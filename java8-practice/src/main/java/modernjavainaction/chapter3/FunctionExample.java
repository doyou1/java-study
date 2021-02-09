package modernjavainaction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * java.util.function.Function<T,R> 인터페이스는 제네릭 형식 T를 인수로 받아서 제네릭 형식 R 객체를 반환하는 추상 메서드 apply를 정의한다.
 * 입력을 출력으로 매핑하는 람다를 정의할 때 Function 인터페이스를 활용할 수 있다.
 */
public class FunctionExample {
    public static void main(String[] args) {
        List<Integer> l = map(
                Arrays.asList("lambdas", "in", "action"),
                (String s) -> s.length()
        );
        System.out.println("l: " + l);
    }
    static <T, R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for(T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}


@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}

