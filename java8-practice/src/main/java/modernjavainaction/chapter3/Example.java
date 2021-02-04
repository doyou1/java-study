package modernjavainaction.chapter3;

import java.util.Comparator;

public class Example {
    public static void main(String[] args) {
        // 기존 코드
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        //람다를 이용한 코드
        Comparator<Apple> byWeightByLambda =
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }
}

class Apple {
    Integer weight;
    String color;

    public Apple() {
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}