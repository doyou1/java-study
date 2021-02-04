package youtube_kevin;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {
    public static void main(String[] args) {
        // 1 2 3 4 5 6 7 8 9
        IntStream.range(1,10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        // 1 2 3 4 5 6 7 8 9 10
        IntStream.rangeClosed(1,10).forEach(i -> System.out.print(i + " "));
        System.out.println();
//        IntStream.iterate(1, i -> i+1).forEach(i -> System.out.print(i + " "));
//        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i-> System.out.print(i+" "));

    }
}
