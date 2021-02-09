package modernjavainaction.chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessFileExample {

    public static void main(String[] args) throws IOException {
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("result: " + result);

        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println("oneLine: " + oneLine);
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("twoLine: " + twoLine);
    }
//    public static String processFile() throws IOException {
//        try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\java8-practice\\src\\main\\java\\modernjavainaction\\chapter3\\data.txt"))) {
//            return br.readLine();
//        }
//    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\java8-practice\\src\\main\\java\\modernjavainaction\\chapter3\\data.txt"))) {
            return p.process(br);
        }
    }
}
