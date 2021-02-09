# 람다 표현식
- 이 장의 내용
    - 람다란 무엇인가?
    - 어디에, 어떻게 람다를 사용하는가?
    - 실행 어라운드 패턴
    - 함수형 인터페이스, 형식 추론
    - 메서드 참조
    - 람다 만들기
    
동작 파라미터화를 이용해서 변화하는 요구사항에 효과적으로 대응하는 코드를 구현할 수 있음을 챕터2에서 확인했다. 또한 정의한 코드 블록을 다른 메서드로 전달할 수 있다.
정의한 코드블록을 특정 이벤트(예를 들면 마우스 클릭)가 발생할 때 실행되도록 설정하거나 알고리즘의 일부('150그램 이상의 사과'와 같은 프레디케이트)로 실행되도록 설정할 수 있다.
따라서 동작파라미터화를 이용하면 더 유연하고 재사용할 수 있는 코드를 만들 수 있다.

익명 클래스로 다양한 동작을 구현할 수 있지만 만족할 만큼 코드가 깔끔하지는 않았다. 깔끔하지 않은 코드는 동작 파라미터를 실전에 적용하는 것을 막는 요소다.
3장에서는 더 깔끔한 코드로 동작을 구현하고 전달하는 자바 8의 새로운 기능인 람다 표현식을 설명한다. 람다 표현식은 익명 클래스처럼 이름이 없는 함수면서 메서드를 인수로 전달할 수 있으므로 일단은 람다표현식이 익명 클래스와 비슷하다고 생각하자.

이 장에서는 람다 표현식을 어떻게 만드는지, 어떻게 사용하는지, 어떻게 코드를 간결하게 만들 수 있는지 설명한다. 또한 자바 8 API에 추가된 중요한 인터페이스와 형식 추론 등의 기능도 확인한다.
마지막으로 람다 표현식과 함께 위력을 발휘하는 새로운 기능인 메서드 참조를 설명한다.

이 장에서는 더 간결하고 유연한 코드를 구현하는 방법을 단계적으로 설명한다. 이 장의 끝부분에서는 배운 개념을 종합한 예제를 보여준다. 2장에서 소개한 정렬 예제를 더 간단하고 가독성 좋은 코드로 바꿀 것이다. 이 장에서 설명하는 내용 자체도 중요하지만 이 장에서 설명하는 람다 표현식은 전체 책에서 광범위하게 사용하므로 이 장의 내용을 완벽하게 이해해야 한다.

### 3.1 람다란 무엇인가?
- 람다 표현식은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라고 할 수 있다. 람다 표현식에는 이름은 없지만, 파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트는 가질 수 있다.
람다의 특징을 살펴보자
    - 익명
        - 보통의 메서드와 달리 이름이 없으므로 익명이라 표현한다. 구현해야 할 코드에 대한 걱정거리가 줄어든다.
    - 함수
        - 람다는 메서드처럼 특정 클래스에 종속되지 않으므로 함수라고 부른다. 하지만 메서드처럼 파라미터 리스트, 바디 반환 형식, 가능한 예외 리스트를 포함한다.
    - 전달
        - 람다 표현식을 메서드 인수로 전달하거나 변수로 저장할 수 있다.
    - 간결성
        - 익명 클래스처럼 많은 자질구레한 코드를 구현할 필요가 없다.
    
람다(lambda)라는 용어는 람다 미적분학 학계에서 개발한 시스템에서 유래했다. 람다 표현식이 왜 중요할까?
2장에서 확인한 것처럼 코드를 전달하는 과정에서 자질구레한 코드가 많이 생긴다. 다행이 람다로 이 문제를 해결할 수 있다.
즉, 람다를 이용해서 간결한 방식으로 코드를 전달할 수 있다. 람다가 기술적으로 자바 8 이전의 자바로 할 수 없었던 일을 제공하는 것은 아니다.
다만 동작 파라미터를 이용할 때 익명 클래스 등 판에 박힌 코드를 구현할 필요가 없다. 람다표현식을 이용하면 2장에서 살펴본 동작 파라미터 형식의 코드를 더 쉽게 구현할 수 있다.
결과적으로 코드가 간결하고 유연해진다. 예를 들어 커스터 Comparator 객체를 기존보다 간단하게 구현할 수 있다.

### 예제 3-1 자바 8의 유효한 람다 표현식
***
(String s) -> s.length()    <- String형식의 파라미터 하나를 가지며 int를 반환한다. 람다 표현식에는 return이 함축되어 있으므로 return 문을 명시적으로 사용하지 않아도 된다.
(Apple a) -> a.getWeight() > 150    <- Apple형식의 파라미터 하나를 가지며 boolean(사과가 150그램보다 무거운지 결정)을 반환함
(int x, int y) -> {
    System.out.println("Result:");      <- int형식의 파라미터 두 개를 가지며 리턴값이 없다(void 리턴). 이 예제에서 볼 수 있듯이 람다 표현식은 여러 행의 문자를 여러 행의 문장을 포함할 수 있다.
    System.out.println(x+y);
}
() -> 42        <- 파라미터가 없다면 int 42를 반환한다.
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())    <- Apple형식의 파라미터 두개를 가지며 int(두 사과의 무게 비교 결과)를 반환한다.
***

### 표 3-1 람다 예제
***
| 사용 사례 | 람다 예제 |
| ------- | ------  |
| Boolean표현식 | (List<String> list) list.isEmpty() | 
| 객체 생성 | new Apple(10) |
| 객체에서 소비 | (Apple a) -> { System.out.println(a.getWeight()) |
| 객체에서 선택/추출 | (String s) -> s.length() |
| 두 값을 조합 | (int a, int b) -> a * b |
| 두 객체 비교 | (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) |
***

### 어디에, 어떻게 람다를 사용할까?
- 함수형 인터페이스라는 문맥에서 람다 표현식을 사용할 수 있다
- 그렇다면, 함수형 인터페이스란?
    - 추상 메서드가 오직 하나면 함수형 인터페이스

퀴즈 3-2 함수형 인터페이스
다음 인터페이스 중 함수형 인터페이스는 ?
```java
    public interface Adder {
        int add(int a, int b);
    }    
    
    public interface  SmartAdder extends Adder {
        int add(double a, double b);
    }
    
    public interface Nothing{
    
    }
```
정답

<code>Adder</code>만 함수형 인터페이스다.

<code>SmartAdder</code>는 두 추상 add 메서드(하나는 <code>Adder</code>에서 상속받음)를 포함하므로 함수형 인터페이스가 아니다.

<code>Nothing</code>은 추상 메서드가 없으므로 함수형 인터페이스가 아니다.

- 함수형 인터페이스로 뭘 할 수 있을까?
    - 람다 표현식으로 함수형 인터페이스의 추상 메서드 구현을 직접 전달할 수 있으므로 전체 표현식을 함수형 인터페이스의 인스턴스로 취급(기술적으로 따지면 함수형 인터페이스를 구현한 클래스의 인스턴스)할 수 있다.
    다음 예제는 Runnable이 오직 하나의 추상 메서드 run을 정의하는 함수형 인터페이스이므로 올바른 코드다.
      
```java
    Runnable r1 = () -> System.out.println("Hello World 1");  // 람다 사용
    Runnalbe r2 = new Runnable(){   // 익명 클래스 사용
        public void run(){
            System.out.println("Hello World 2");    
        }
    };
    
    public static void process(Runnable r) {
        r.run();
    }
    process(r1);    // "Hello World 1" 출력
    process(r2);    // "Hello World 2" 출력
    process(() -> System.out.println("Hello World 3")); // 직접 전달된 람다 표현식으로 "Hello World 3" 출력
```

퀴즈 3-3 어디에 람다를 사용할 수 있는가?

다음 중 람다 표현식을 올바로 사용한 코드는?
1. execute(() -> {});  
    public void execute(Runnable r) {
        r.run();
    }
   
2. public Callable<String> fetch() {
        return () -> "Tricky example ;-)";
    }
   
3. Predicate<Apple> p = (Apple a) -> a.getWeight();

정답

1번과 2번은 유효한 람다 표현식

첫 번째 예제에서 람다 표현식 () -> {}의 시그니처는 () -> void며 Runnable의 추상 메서드 run의 시그니처와 일치하므로 유효한 람다 표현식이다. 다만 람다의 바디가 비어있으므로 이 코드를 샐항하면 아무 일도 일어나지 않는다.

두 번째 예제도 유효한 람다 표현식이다. fetch 메서드의 반환 형식은 Callable<String>이다. T를 String으로 대치했을 때 Callable<String> 메서드의 시그니처는 () -> String이 된다. () -> "Tricky example ;-)";는 () -> String 시그니처이므로 문맥상 유효한 람다 표현식이다.

세 번째 예제에서 람다 표현식(Apple a) -> a.getWeight()의 시그니처는 (Apple) -> Integer이므로 Predicate<Apple> : (Apple) -> boolean의 test 메서드의 시그니처와 일치하지 않는다. 따라서 유효한 람다 표현식이 아니다.

***
@FunctionalInterface는 무엇인가?

새로운 자바 API를 살펴보면 함수형 인터페이스에 @FunctionalInterface 어노테이션이 추가되어 있다.(3.4절에서 함수형 인터페이스를 자세히 살펴보면서 더 많은 리스트를 소개할 것이다) @FunctionalInterfac는 함수형 인터페이스임을 가리키는 어노테이션이다.
@FuncationalInterface로 인터페이스를 선언했지만 실제로 함수형 인터페이스가 아니면 컴파일러가 에러를 발생시킨다. 예를 들어 추상 메서드가 한 개 이상이라면 "Multiple nonoverriding abstract methods found in interface Foo(인터페이스 Foo에 오버라이드 하지않은 여러 추상 메서드가 있음)"같은 에러가 발생할 수 있다.
***

### 3.3 람다 활용 : 실행 어라운드 패턴

람다와 동작 파라미터화로 유연하고 간결한 코드를 구현하는 데 도움을 주는 실용적인 예제를 살펴보자.
자원 처리(예를 들면 데이터베이스의 파일 처리)에 사용하는 순환 패턴(recurrent pattern)은 자원을 열고, 처리한 다음에, 자원을 닫는 순서로 이루어진다. 설정(setup)과 정리(cleanup) 과정은 대부분 비슷하다.
즉, 실제 자원을 처리하는 코드를 설정과 정리 두 과정이 둘러싸는 형태를 갖는다. [그림 3-2]와 같은 형식의 코드를 실행 어라운드 패턴(execute around pattern)이라고 부른다.
다음 예제에서 굵은 글씨는 파일에서 한 행을 읽는 코드다(예제는 자바7에 새로 추가된 try-with-resources 구문을 사용했다. 이를 사용하면 자원을 명시적으로 닫을 필요가 없으므로 간결한 코드를 구현하는 데 도움을 준다.)

```java
public String processFile() throws IOException {
    try(BufferedReader br = 
            new BufferedReader(new FileReader("data.txt"))) {
        return br.readLine();     
    }
}
```

### 3.3.1 1단계 : 동작 파라미터화를 기억하라

현재 코드는 파일에서 한 번에 한 줄만 읽을 수 있다. 한 번에 두 줄을 읽거나 가장 자주 사용되는 단어를 반환하려면 어떻게 해야 할까?
기존의 설정, 정리 과정은 재사용하고 processFile 메서드만 다른 동작을 수행하도록 명령할 수 있다면 좋을 것이다. 이미 익숙한 시나리오 아닌가? 그렇다.
processFile의 동작을 파라미터화하는 것이다. processFile 메서드가 BufferedReader를 이용해서 다른 동작을 수행할 수 있도록 processFile 메서드로 동작을 전달해야 한다.

### 3.4 함수형 인터페이스 사용

함수형 인터페이스는 오직 하나의 추상 메서드를 지정한다. 함수형 인터페이스의 추상 메서드는 람다 표현식의 시그니처를 묘사한다. 함수형 인터페이스의 추상 메서드 시그니처를 함수 디스크립터(function descriptor)라고 한다. 다양한 람다 표현식을 사용하려면 공통의 함수 디스크립터를 기술하는 함수형 인터페이스 집합이 필요하다.
3.2절에서 살펴본 것처럼 이미 자바 API는 Comparable, Runnable, Callable 등의 다양한 함수형 인터페이스를 포함하고 있다.
자바 8 라이브러리 설계자들은 java.util.function 패키지로 여러 가지 새로운 함수형 인터페이스를 제공한다. 이 절에서는 Predicate, Consumer, Function 인터페이스를 설명한다.

### 기본형 특화
제네릭 함수형 인터페이스 Predicate<T>, Consumer<T>, Function<T,R>이 있고, 특화된 형식의 함수형 인터페이스도 있다.

자바의 모든 형식은 참조형(reference type)(예를 들면 Byte, Integer, Object, List) 아니면 기본형(primitive type)(예를 들면 int, double, byte, char)에 해당한다. 하지만 제네릭 파라미터(예를 들면 Consumer<T>의 T)에는 참조형만 사용할 수 있다.
제네릭의 내부 구현 때문에 어쩔 수 없는 일이다. 자바에서는 기본형을 참조형으로 변환하는 기능을 제공한다. 이 기능을 박싱(boxing)이라고 한다.
참조형을 기본형으로 변환하는 반대 동작을 언박싱(unboxing)이라고 한다. 또한 프로그래머가 편리하게 코드를 구현할 수 있도록 박싱과 언박싱이 자동으로 이루어지는 오토박싱(autoboxing)이라는 기능도 제공한다. 예를 들어 다음은 유효한 코드다(int가 integer로 박싱됨)
```java
List<Integer> list = new ArrayList<>();
for(int i=300; i<400; i++){
    list.add(i);    
}
```

하지만 이런 변환 과정은 비용이 소모된다. 박싱한 값은 기본형을 감싸는 래퍼며 힙에 저장된다. 따라서 박싱한 값은 메모리를 더 소모하며 기본형을 가져올 때도 메모리를 탐색하는 과정이 필요하다.
자바 8에서는 기본형을 입출력으로 사용하는 상황에서 오토박싱 동작을 피할 수 있도록 특별한 버전의 함수형 인터페이스를 제공한다.
예를 들어 아래 예제에서 IntPredicate는 1000이라는 값을 박싱하지 않지만, Predicate<Integer>는 1000이라는 값을 Integer 객체로 박싱한다.

```java
public interface IntPredicate{
    boolean test(int t);
}

IntPredicate evenNumbers = (int i) -> i % 2 == 0;
evenNumbers.test(1000);     // 참(박싱 없음)

Predicate<Integer> oddNumbers = (Integer i) -> i%2 != 0;
oddNumbers.test(1000);      // 거짓(박싱)
```

일반적으로 특정 형식을 입력으로 받는 함수형 인터페이스의 이름 앞에는 DoublePredicate, IntConsumer, LongBinaryOperator, IntFunction처럼 형식명이 붙는다.
Function 인터페이스는 ToIntFunction<T>, IntToDoubleFunction 등의 다양한 출력 형식 파라미터를 제공한다.
[표 3-2]는 자바 API에서 제공하는 대표적인 함수형 인터페이스와 함수 디스크립터를 보여준다. 필요하다면 우리가 직접 함수형 인터페이스를 만들 수 있다. (T, U) -> R 같은 표기법으로 함수 디스크립터를 설명할 수 있음을 기억하자.
표에서 왼쪽 코드는 인수 형식을 가리킨다. 예제의 표기법은 제네릭 형식 T와 U를 인수로 받으며 R을 반환하는 함수다.

***
|함수형 인터페이스 | 함수 디스크립터 | 기본형 특화 |
|--------------|---------------|----------|
| Predicate<T> | T -> boolean | IntPredicate, LongPredicate, DoublePredicate |
| Consumer<T> | T -> void | IntCousumer, LongConsumer, DoubleConsumer |
| Function<T,R> | T -> R | IntFunction<R>, IntToDoubleFunction, IntToLongFunction, LongFunction<R>, LongToDoubleFunction, LongToIntFunction, DoubleFunction<R>, DoubleToIntFunction, DoubleToLongFunction, ToIntFunction<T>, ToDoubleFunction<T>, ToLongFunction<T> |
| Supplier<T> | () -> T | BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier |
| UnaryOperator<T> | T -> T | IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator |
| BinaryOperator<T> | (T,T) -> T | IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator |
| BiPredicate<L,R> | (T,U) -> boolean | |
| BiConsumer<T,U> | (T,U) -> void | ObjIntConsumer<T>, ObjLongConsumer<T>, ObjDoubleConsumer<T> |
| BiFunction<T,U,R> | (T,U) -> R | ToIntBiFunction<T,U>, ToLongBiFunction<T,U>, ToDoubleBiFunction<T,U> |
***

### Quiz 3-4 함수형 인터페이스
1. T -> R               // Function<T,R>, T 형식의 객체를 R 형식의 객체로 변환할 때 (Function<Apple,Integer>로 사과의 무게 정보 추출)
2. (int, int) -> int    // IntBinaryOperator는 (int, int) -> int 형식의 시그니처를 갖는 추상 메서드 applyAsInt를 정의한다.
3. T -> void            // Consumer<T>는 T -> void 형식의 시그니처를 갖는 추상 메서드 accept를 정의한다
4. () -> T              // Supplier<T>는 () -> T 형식의 시그니처를 갖는 추상 메서드 get을 정의한다. 또한 Callable<T>도 () -> T 형식의 시그니처를 갖는 추상 메서드 call을 정의한다.
5. (T,U) -> R           // BiFunction<T,U,R>은 (T,U) -> R 형식의 시그니처를 갖는 추상 메서드 apply를 정의한다.

### 표 3-3 람다와 함수형 인터페이스 예제
|   사용사례    |   람다예제    |   대응하는 함수형 인터페이스  |
|-------------|--------------|--------------------------|
| 불리언표현     | (List<String> list) -> list.isEmpty() | Predicate<List<String>> |
| 객체 생성      | () -> new Apple(10) | Supplier<Apple> |
| 객체에서 소비   | (Apple a) -> System.out.println(a.getWeight())    | Consumer<Apple> |
| 객체에서 선택/추출 | (String s) -> s.length() | Function<String,Integer> 또는 ToIntFunction<String> |
| 두 값 조합    | (int a, int b) -> a * b       | IntBinaryOperator |
| 두 객체 비교 | (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()) | Comparator<Apple> 또는 BiFunction<Apple, Apple, Integer> 또는 ToIntBiFunction<Apple,Apple> |

### 예외, 람다, 함수형 인터페이스의 관계

함수형 인터페이스는 확인된 예외를 던지는 동작을 허용하지 않는다. 즉, 예외를 던지는 람다 표현식을 만들려면 확인된 예외를 선언하는 함수형 인터페이스를 직접 정의하거나 람다를 try/catch 블록으로 감싸야 한다.
예를 들어 3.3절에서 등장했던 IOException을 명시적으로 선언하는 함수형 인터페이스 BufferedReaderProcessor를 살펴보자.

```java
import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
BufferedReaderProcessor p = (BufferedReader br) -> br.readLine();
```

그러나 우리는 Function<T,R> 형식의 함수형 인터페이스를 기대하는 API를 사용하고 있으며 직접 함수형 인터페이스를 만들기 어려운 상황이다. 이런 상황에서는 다음 예제처럼 명시적으로 확인된 예외를 잡을 수 있다.
```java
Function<BufferedReader, String> f = (BufferedReader b) -> {
    try{
        return b.readLine();    
    } catch(IOException e) {
        throw new RuntimeException(e);    
    }
}
```

# 3.5 형식 검사, 형식 추론, 제약

람다 표현식을 처음 설명할 때 람다로 함수형 인터페이스의 인스턴스를 만들 수 있다고 언급했다. 람다 표현식 자체에는 람다가 어떤 함수형 인터페이스를 구현하는지의 정보가 포함되어 있지 않다. 따라서 람다 표현식을 더 제대로 이해하려면 람다의 실제 형식을 파악해야 한다.

### 3.5.1 형식 검사

람다가 사용되는 콘텍스트(context)를 이용해서 람다의 형식(type)을 추론할 수 있다. 어떤 콘텍스트(예를 들면 람다가 전달된 메서드 파라미터나 람다가 할당되는 변수 등)에서 기대되는 람다 표현식의 형식을 대상 형식이라고 부른다. 람다 표현식을 사용할 때 실제 어떤 일이 일어나는지 보여주는 예제를 확인하자.
```java
List<Apple> heavierThan150g =
filter(inventory, (Apple apple) -> apple.getWeight() > 150);
```

- 위 코드의 형식 확인 과정
    1. filter 메서드의 선언을 확인한다.
    2. filter 메서드는 두 번째 파라미터로 Predicate<Apple> 형식(대상 형식)을 기대한다.
    3. Predicate<Apple>은 test라는 한 개의 추상 메서드를 정의하는 함수형 인터페이스다.
    4. test 메서드는 Apple을 받아 boolean을 반환하는 함수 디스크립터를 묘사한다.
    5. filter 메서드로 전달된 인수는 이와 같은 요구사항을 만족해야 한다.
    
### 3.5.2 같음 람다, 다른 함수형 인터페이스

대상 형식(target typing)이라는 특징 때문에 같은 람다 표현식이더라도 호환되는 추상 메서드를 가진 다른 함수형 인터페이스로 사용될 수 있다.
예를 들어 이전에 살펴본 Callable과 PrivilegedAction 인터페이스는 인수를 받지 않고 제네릭 형식 T를 반환하는 함수를 정의한다. 따라서 다음 두 할당문은 모두 유효한 코드다.
```java
Callable<Integer> c = () -> 42;
PrivilegedAction<Integer> p = () -> 42;
```

하나의 람다 표현식을 다양한 함수형 인터페이스에 사용할 수 있다.
```java
Comparator<Apple> c1 =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
ToIntBiFunction<Apple, Apple> c2 =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
BiFunction<Apple, Apple, Integer> c3 =
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```

- 다이아몬드 연산자

자바의 변화에 익숙한 독자라면 이미 자바 7에서도 다이아몬드 연산자(<>)로 콘텍스트에 따른 제네릭 형식을 추론할 수 있다는 사실을 기억할 것이다(제네릭 메서드에서 이런 개념을 쉽게 찾아볼 수 있다).
주어진 클래스 인스턴스 표현식을 두 개 이상의 다양한 콘텍스트에 사용할 수 있다. 이때 인스턴스 표현식의 형식 인수는 콘텍스트에 의해 추론된다.
```java
List<String> listOfStrings = new ArrayList<>();
List<Integer> listOfIntegers = new ArrayList<>();
```

- 특별한 void 호환 규칙

람다의 바디에 일반 표현식이 있으면 void를 반환하는 함수 디스크립터와 호환된다(물론 파라미터 리스트도 호환되어야 함). 예를 들어 다음 두 행의 예제에서 List의 add 메서드는 Consumer 콘텍스트(T -> void)가 기대하는 boolean을 반환하지만 유효한 코드다.
```java
// Predicate는 불리언 반환값을 갖는다.
Predicate<String> p = s -> list.add(s);
// Consumer는 void 반환값을 갖는다.
Consumer<String> b = s -> list.add(s);
```
