package modernjavainaction.chapter2;

import java.util.ArrayList;
import java.util.List;

public class Quiz2_1 {
    // 유연한 prettyPrintApple 메서드 구현

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        // A light green apple
        // A heavy red apple
        prettyPrintApple(inventory, new AppleFancyFormatter());

        // An apple of 80g
        // An apple of 155g
        prettyPrintApple(inventory, new AppleSimpleFormatter());

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for(Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}

interface AppleFormatter {
    String accept(Apple a);
}

class AppleFancyFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" :
                "light";
        return "A " + characteristic +
                " " + apple.getColor() + " apple";
    }
}

class AppleSimpleFormatter implements AppleFormatter {
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}