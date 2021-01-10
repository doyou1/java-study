import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class WhyJava8 {

    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        final StringBuilder stringBuilder = new StringBuilder();

//                for(Integer number : numbers) {
//            stringBuilder.append(number).append(" : ");
//        }
//         1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9 : 10 :
//         10 뒤의 :를 깔끔하게 없애고 싶다
//        System.out.println(stringBuilder.toString());

//        final int size = numbers.size();
//        for(int i=0;i<size;i++) {
//            stringBuilder.append(numbers.get(i));
//            if(i!=size-1){
//                stringBuilder.append(" : ");
//            }
//        }
//        1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9 : 10
//         코드가 지저분, 미스코딩의 가능성
//        System.out.println(stringBuilder.toString());

//        final String separator = " : ";
//        for(Integer number : numbers) {
//            stringBuilder.append(number).append(" : ");
//            stringBuilder.append(number).append(separator);
//        }
//        if(stringBuilder.length() > 0) {
//            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
//            stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
//        }

//      1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9 : 10
//      지저분, 3(매직넘버)이라는 상수 사용, 메소드 활용법 매번 확인하게 됨, 코드 이해의 어려움
        System.out.println(stringBuilder.toString());

//      BUT!!! JAVA 8이라면?
        final String result = numbers.stream()
                .map(String::valueOf)
                .collect(joining(" : "));
//        1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9 : 10
        System.out.println(result);
        
    }
}
