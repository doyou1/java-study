package modernjavainaction.chapter2;

import java.util.ArrayList;
import java.util.List;

import static modernjavainaction.chapter2.Color.GREEN;

public class Example {
    public static void main(String[] args) {

        Apple apple1 = new Apple();
        apple1.setColor("GREEN");
        System.out.println(GREEN.equals(Enum.valueOf(Color.class,apple1.getColor())));
    }

    // 녹색 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>(); // 사과 누적 리스트
        for(Apple apple : inventory) {
            if(GREEN.equals(Enum.valueOf(Color.class,apple.getColor()))) {
                result.add(apple);
            }
        }
        return result;
    }

    // 색을 파라미터화
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(color.equals(Enum.valueOf(Color.class, apple.getColor()))) {
                result.add(apple);
            }
        }
        return result;
    }

    // 무게 정보 필터링
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    // 가능한 모든 속성 필터링
    public static List<Apple> filterApples(List<Apple> inventory, Color color,
                                           int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            // 색이나 무게를 선택하는 방법이 마음에 들지 않음
            if((flag && color.equals(Enum.valueOf(Color.class, apple.getColor())))
                || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }

        return result;
    }

    /*
    public class AppleRedAndHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return RED.equals(apple.getColor())
                && apple.getWeight() > 150;
        }
    }


    List<Apple> redAndHeavyApples =
        filterApples(inventory, new AppleRedAndHeavyPredicate());
    */
    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

enum Color {
    RED,
    GREEN
}

class Apple{
    String color;
    Integer weight;

    public Apple(){

    }
    public Apple(int weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}