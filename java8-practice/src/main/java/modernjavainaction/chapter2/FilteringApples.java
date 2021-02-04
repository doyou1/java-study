package modernjavainaction.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static modernjavainaction.chapter2.Color.GREEN;
import static modernjavainaction.chapter2.Color.RED;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "GREEN"),
                                                new Apple(155, "GREEN"),
                                                new Apple(120, "RED"));
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        List<Apple> heavyApples =
                filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println("heavyApples: " + heavyApples.size());
        List<Apple> greenApples =
                filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("greenApples: " + greenApples.size());

        // 익명 클래스 사용
        List<Apple> redApples =
                filterApples(inventory, new ApplePredicate() {
                    @Override
                    public boolean test(Apple apple) {
                        return RED.equals(Enum.valueOf(Color.class, apple.getColor()));
                    }
                });
        System.out.println("redApples: " + redApples.size());

        // 람다 표현식 사용
        List<Apple> result =
                filterApples(inventory, (Apple apple) -> RED.equals(Enum.valueOf(Color.class, apple.getColor())));
        System.out.println("result: " + result.size());

        // 리스트 형식으로 추상화
        List<Apple> redApplesByList =
                filter(inventory, (Apple apple) -> RED.equals(Enum.valueOf(Color.class, apple.getColor())));
        System.out.println("redApplesByList: " + redApplesByList.size());

        List<Integer> evenNumbers =
                filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println("evenNumbers: " + evenNumbers.size());
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}

class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}

class AppleGreenColorPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return GREEN.equals(Enum.valueOf(Color.class, apple.getColor()));
    }
}

interface Predicate<T> {
    boolean test(T t);
}

