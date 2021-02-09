package modernjavainaction.chapter3;

import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {

        String name = "JaeHyeong";
        Supplier<String> s = () -> name;

        doit("Hello ", s);


    }

    static <T> void doit(String sentence,Supplier<T> s) {
        System.out.println(sentence + s.get());
    }
}
