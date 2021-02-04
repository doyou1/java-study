package youtube_kevin;

import java.math.BigDecimal;

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        println(1,2,3,(t1,t2,t3) -> String.valueOf(t1+t2+t3));
        println(1,2,3,(t1,t2,t3) -> t1+""+t2+""+t3);

//      넓이 구하기
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));

        println(1L,"jh","test@email.com"
                , (id, name, email) ->
                        "User info: ID= " + id + ", name= "+name+", email= " + email);

//        BigDecimal a = new BigDecimal("10");
//        BigDecimal b = new BigDecimal("3");
//        System.out.println(a.add(b));
//        System.out.println(a);
//        System.out.println(b);
//        BigDecimal result = a.add(b);
//        System.out.println(result);

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("123.1")));

        final InvalidBigDecimalToCurrency anonymous = new InvalidBigDecimalToCurrency() {
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };
        System.out.println("anonymous class : " + anonymous.mkString(123));

        // Target method is generic
        // generic 파라미터는 람파 표현식 사용불가
//        final InvalidBigDecimalToCurrency invalidBigDecimalToCurrency = value -> value.toString();
//        System.out.println("invalid : " + invalidBigDecimalToCurrency.mkString(123));
    }

    private static <T1,T2,T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1,T2,T3, String> function) {
        System.out.println(function.apply(t1,t2,t3));
    }


}

// 함수형 인터페이스엔 추상메소드가 단 1개 있어야한다!
@FunctionalInterface
interface Function3<T1,T2,T3,R> {
    R apply(T1 t1,T2 t2,T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidBigDecimalToCurrency {
    <T> String mkString(T value);
}