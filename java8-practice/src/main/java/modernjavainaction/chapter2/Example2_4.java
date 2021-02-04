package modernjavainaction.chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example2_4 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "GREEN"),
                new Apple(155, "GREEN"),
                new Apple(120, "RED"));

        // Comparator로 정렬하기
//        inventory.sort(new Comparator<Apple>() {
//            public int compare(Apple a1, Apple a2) {
////                return a1.getWeight().compareTo(a2.getWeight());
//                return a2.getWeight().compareTo(a1.getWeight());
//            }
//        });
        inventory.sort(
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
        );
        System.out.println(inventory);

        // Runnable로 코드 블록 실행하기
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello World");
//            }
//        });
//        t.run();
        Thread t = new Thread(() -> System.out.println("Hello World"));
        t.run();

        // Callable을 결과로 반환하기
        ExecutorService executorService = Executors.newCachedThreadPool();
//        Future<String> threadName = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return Thread.currentThread().getName();
//            }
//        });
        Future<String> threadName = executorService.submit(
                () -> Thread.currentThread().getName()
        );
        System.out.println(threadName.toString());

        // GUI 이벤트 처리하기
//        Button button = new Button("Send");
//        button.set(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event){
//                label.setText("Sent!!");
//            }
//        });
//
//        button.setOnAction((ActionEvent event) -> label.setText("Sent!"));

    }
}
