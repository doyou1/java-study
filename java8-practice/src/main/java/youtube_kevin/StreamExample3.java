package youtube_kevin;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample3 {
    public static void main(String[] args) {
        System.out.println("collect(toList()):" +
            Stream.of(1,3,3,5,5)
                    .filter(i -> i>2)
                    .map(i -> i*2)
                    .map(i->"#" + i)
                    .collect(toList())
        );
        System.out.println("collect(toSet()):" +
                Stream.of(1,3,3,5,5)
                        .filter(i -> i>2)
                        .map(i -> i*2)
                        .map(i->"#" + i)
                        .collect(toSet())
        );
        System.out.println("collect(joining()):" +
                Stream.of(1,3,3,5,5)
                        .filter(i -> i>2)
                        .map(i -> i*2)
                        .map(i->"#" + i)
                        .collect(joining())
        );
        System.out.println("collect(joining(\", \")):" +
                Stream.of(1,3,3,5,5)
                        .filter(i -> i>2)
                        .map(i -> i*2)
                        .map(i->"#" + i)
                        .collect(joining(", "))
        );
        System.out.println("collect(joining(\", \", \"[\", \"]\")):" +
                Stream.of(1,3,3,5,5)
                        .filter(i -> i>2)
                        .map(i -> i*2)
                        .map(i->"#" + i)
                        .collect(joining(", ", "[", "]"))
        );
        System.out.println("distinct().collect(joining(\", \", \"[\", \"]\")):" +
                Stream.of(1,3,3,5,5)
                        .filter(i -> i>2)
                        .map(i -> i*2)
                        .map(i->"#" + i)
                        .distinct()
                        .collect(joining(", ", "[", "]"))
        );

        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1,2,3,4,5,127)
                    .filter(i -> i == integer127)
                    .findFirst()
        );

        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1,2,3,4,5,128)
                        .filter(i -> i == integer128)
                        .findFirst()
        );

        System.out.println(".filter(i -> i > 3).count(): "+
                Stream.of(1,2,3,4,5)
                        .filter(i -> i > 3)
                        .count()
        );
        final Integer integer3 = 3;
        System.out.println(".filter(i -> i > integer3).count(): "+
                Stream.of(1,2,3,4,5)
                        .filter(i -> i > integer3)
                        .count()
        );
        System.out.println(".filter(i -> i.equals(integer3)).count(): "+
                Stream.of(1,2,3,4,5)
                        .filter(i -> i.equals(integer3))
                        .count()
        );
    }
}