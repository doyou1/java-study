package modernjavainaction.chapter3;

import java.util.Arrays;
import java.util.List;

/**
 * java.util.function.Consumer<T> 인터페이스는 제네릭 형식 T 객체를 받아서 void를 반환하는 accept라는 추상 메서드를 정의한다. T 형식의 객체를 인수로 받아서 어떤 동작을 수행하고 싶을 때 Consumer 인터페이스를 사용할 수 있다.
 */
public class ConsumerExample {
    public static void main(String[] args) {

        forEach(Arrays.asList(1,2,3,4,5),
                (Integer i) -> System.out.println(i));
    }
    static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T t : list) {
            c.accept(t);
        }
    }
}

@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}
