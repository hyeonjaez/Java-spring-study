package org.example;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Precondition 클래스는 전제 조건을 확인하기 위한 유틸리티 메서드를 제공한다.
 * 이 클래스는 Argument가 null인지 여부를 확인하는 메서드와 사용자 정의 Predicate에 대한 확인 메서드를 포함한다.
 */
public class Precondition {

    /**
     * Argument 값이 null인지 확인합니다.
     *
     * @param value 확인할 Argument.
     * @param <T>   값의 유형.
     * @throws IllegalArgumentException 값이 null인 경우 발생합니다.
     *                                  NullPointerException이 아닌 IllegalArgumentException인 이유
     *                                  <ul>
     *                                      <li>1. NullPointerException : 이 예외는 주로 객체 참조가 'null'인 상태에서 객체의 멤버나 메서드에 접근 하려 고 할때 발생한다 즉, 객체자체가 null인 상태에서 메서드 호출이나 필드 참조를 시도했을때 발생한다.</li>
     *                                      <li>2. IllegalArgumentException : 이 예외는 주로 메서드에 전달된 인자의 값이 유효하지 않은 경우에 발생한다. 예를 들어, 메서드에 전달된 인자가 특정 범위를 벗어나거나 특정 조건을 만족하지 않을 때 사용 된다.</li>
     *                                      <li>3. 그래서 왜 IllegalArgumentException을 선택 했는가  </li>
     *                                       <li>3.1 : 의미상의 일치 : 'IllegalArgumentException'은 주로 메서드의 인자로 전달되는 값이 허용되지 않은 경우에 사용된다.</li>
     *                                       <li>3.2 : 명확성과 가독성 : 'NullPointerException'을 던졌다면, 해당 예외가 메서드에서 발생했는지 특정 객체가 null을 허용을 안해서 발생했는지 명확하게 전달하기 어렵다. 반면 'IllegalArgumentException'은 인자의 문제를 명시적으로 나타내는데 도움이 된다.</li>
     *                                       <li>3.3 : 코드 일관성 : 전반적인 모든 코드에서는 메서드의 인자 값이 유효하지 않은 경우에 IllegalArgumentException을 던지게 되어 있다 해당 메서드도 받은 인자 값이 null 이란 값을 유효하지 않다고 보고 있다 그러하여 IllegalArgumentException을 던지는게 일관성 있다고 보고 있다.</li>
     *                                  </ul>
     */
    public static <T> void checkArgumentIsNull(T value) {
        checkArgument(Objects::isNull, value);
    }

    /**
     * Argument 값에 대해 사용자가 원하는 조건을 정의하는 Predicate를 확인한다.
     *
     * @param predicate 확인할 Predicate
     * @param value     확인할 값
     * @param <T>       값의 유형
     * @throws IllegalArgumentException Predicate가 참인 경우 발생한다.
     */
    public static <T> void checkArgument(Predicate<T> predicate, T value) {
        checkArgument(predicate.test(value));
    }


    private static void checkArgument(boolean flag) {
        if (flag) {
            throw new IllegalArgumentException("");
        }
    }


    private Precondition() {
    }
}
