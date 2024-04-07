package org.example.functional;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 이 클래스는 long 값의 구간을 나타낸다. 구간은 시작값과 종료값에 의해 정의된다.
 * 이 클래스는 open, closed, openClosed 등 구간을 포함하는 다양한 유형의 범위를 만들기 위한 정적 팩토리 메서드를 통해 제공한다.
 * 클래스에는 시작값과 종료값이 포함되어 있고 해당 범위를 반복할 수 있도록 Iterable<Long> 인터페이스를 구현한다.
 * <p>
 * 정적 팩토리 메서드의 단점
 * <ul>
 *     <li>1. Increased binding : 정적 팩토리 메서드를 사용하면 해당 클래스와 그것을 사용하는 클라이언트 간의 결합도가 높아 질 수 있다. 생성자를 사용하는 경우와는 달리, 정적 팩토리 메서드는 특정 클래스에 종속되기 때문이다.</li>
 *     <li>2. non-scalable : 정적 팩토리 메서드는 정적으로 바인딩되기 때문에 하위 클래스에서 오버라이딩 할 수 없다. 이로 인하여 서브 타입의 유연성이 감소 한다. </li>
 * </ul>
 * </p>
 *
 * <p>
 * 그럼에도 불구 하고 정적 팩토리 메서드를 한 이유
 * <ul>
 *      <li>1. readability and clarity : 정적 팩토리 메서드는 객체 생성에 대한 의도를 명확하게 들어낼 수 있다.</li>
 *      <li>2. Design flexibility : 정적 팩토리 메서드를 사용하면 객체 생성 로직을 다양하게 할 수 있다.</li>
 *      <li>3. 수학적 Range 의미 : range가 수학적으로 두 실수 a,b (b는 a보다 크거나 같다)에 대해, a,b사이의 모든 실수의 집합이다. 이 구간마다 구간의 종류가 다양하게 있는데
 * 닫힌구간, 열린구간, 반열린구간, 반닫힌구간 등등이 있다. 이 구간의 종류마다 이 Range 클래스에서 생성하게 만들고
 * 이름을 명확하게 하기 위해서 정적 팩토리 메서드를 사용하여 각 구간의 의미에 맞는 인스턴스를 생성하게 해주기 위해서 사용하였다.</li>
 * </ul>
 *  </p>
 */

public final class Range implements Iterable<Long> {
    private final long startInclusive;
    private final long endExclusive;

    /**
     * 시작값(포함) 및 종료값(포함)으로 새로운 인스턴스를 생성한다.
     * private 생성자
     * <p>이 생성자는 클래스 불변성을 유지하기 위하여
     * 종료 값이 시작 값보다 크거나 같은지를 확인한다.</p>
     *
     * @param startInclusive 구간의 시작 값이다.
     * @param endExclusive   구간의 종료 값이다.
     * @throws IllegalArgumentException 종료 값이 시작 값보다 작으면 클래스 불변성을 위반하기 때문에 발생하게 했다.
     */
    private Range(long startInclusive, long endExclusive) {
        classInvariant(startInclusive, endExclusive);
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    /**
     * 시작 값 초과, 종료 값 미만의 구간을 생성 하는 static factory method. a,b -> (a,b)
     *
     * @param startInclusive 구간의 시작 값
     * @param endExclusive   구간의 종료 값
     * @return 조건으로 생성된 구간을 나타내는 Range 인스턴스
     */
    public static Range open(long startInclusive, long endExclusive) {
        return new Range(Math.addExact(startInclusive, 1L), Math.subtractExact(endExclusive, 1L));
    }

    /**
     * 시작 값 이상, 종료 값 이하의 구간을 생성 하는 static factory method. a,b -> [a,b]
     *
     * @param startInclusive 구간의 시작 값
     * @param endInclusive   구간의 종료 값
     * @return 조건으로 생성된 구간을 나타내는 Range 인스턴스
     */
    public static Range closed(long startInclusive, long endInclusive) {
        return new Range(startInclusive, endInclusive);
    }

    /**
     * 시작 값 이상, 종료값 미만의 구간을 생성 하는 static factory method. a,b -> [a,b)
     *
     * @param startInclusive 구간의 시작 값
     * @param endExclusive   구간의 종료 값
     * @return 조건으로 생성된 구간을 나타내는 Range 인스턴스
     */
    public static Range closedOpen(long startInclusive, long endExclusive) {
        return new Range(startInclusive, Math.subtractExact(endExclusive, 1L));
    }

    /**
     * 시작 값 미만, 종료값 이상의 구간을 생성하는 static factory method. a,b -> (a,b]
     *
     * @param startInclusive 구간의 시작 값
     * @param endExclusive   구간의 종료 값
     * @return 조건으로 생성된 구간을 나타내는 Range 인스턴스
     */
    public static Range openClosed(long startInclusive, long endExclusive) {
        return new Range(Math.addExact(startInclusive, 1L), endExclusive);
    }


    public static Range is(long exclusive) {
        return new Range(exclusive, exclusive);
    }

    /**
     * Long 타입의 최솟값 부터 받은 종료값 이하의 구간을 생성하는 static factory method.
     *
     * @param endExclusive 구간의 종료 값 (포함)
     * @return long타입의 최솟값 부터 종료값이하의 구간을 나타내는 인스턴스
     */
    public static Range fromMinValue(long endExclusive) {
        return new Range(Long.MIN_VALUE, endExclusive);
    }

    /**
     * 주어진 최솟값 이상 Long 타입의 최댓값 이하의 구간을 생성하는 static factory method.
     *
     * @param startInclusive 구간의 시작 값 (포함)
     * @return 시작 값 이상 Long 타입의 이하의 구간을 나타내는 인스턴스
     */
    public static Range toMaxValue(long startInclusive) {
        return new Range(startInclusive, Long.MAX_VALUE);
    }

    /**
     * Long 타입의 최솟값 이상 최댓값 이하의 모든 값을 포함하는 구간을 생성하는 static factory method.
     *
     * @return Long 타입의 최솟값 이상 최댓값 이하의 구간을 나타내는 Range 인스턴스
     */
    public static Range all() {
        return new Range(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 클래스 불변성을 유지하기 위한 private 메서드
     * 종료 값이 시작 값보다 작으면 에러를 던진다.
     *
     * @param startInclusive 구간의 시작 값
     * @param endExclusive   구간의 종료 값
     * @throws IllegalArgumentException 종료값이 시작 값보다 작아 클래스 불변성을 위반할 경우 발생한다.
     */
    private void classInvariant(long startInclusive, long endExclusive) {
        if (endExclusive < startInclusive) {
            throw new IllegalArgumentException("Range: " + startInclusive + " > " + endExclusive);
        }
    }

    /**
     * 구간의 종료 값, 다시 말해 구간의 최댓값을 반환한다.
     *
     * @return 구간의 종료값(구간의 최댓값)
     */
    public long max() {
        return this.endExclusive;
    }

    /**
     * 구간의 시작 값, 다시 말해 구간의 최솟값을 반환한다
     *
     * @return 구간의 시작값(구간의 최솟값)
     */
    public long min() {
        return this.startInclusive;
    }

    /**
     * 구간안에 포함된 값들의 개수, 구간의 크기를 BigInteger형으로 반환하는 메서드이다.
     * 구간의 크기가 Long 타입의 최댓값 보다 커지면 에러를 던지기 때문에 size를 구할때 BigInteger형으로 하였다.
     * 마지막에 1을 더한 이유는 종료 값도 1를 더함으로 있어  종료값도 구간에 포함되도록 하기 위합니다.
     *
     * @return 구간의 크기를 나타내는 BigInteger 값
     */
    public BigInteger size() {
        BigInteger max = BigInteger.valueOf(this.max());
        BigInteger min = BigInteger.valueOf(this.min());

        return max.subtract(min).add(BigInteger.ONE);
    }


    /**
     * 구간에 속하는 값을 최솟값 부터 최댓값까지 요소를 생성하는 Iterator를 반환하는 메서드이다.
     *
     * @return 구간에 속하는 값을 반복적으로 제공하는 Iterator<Long> 인스턴스
     */

    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            private boolean isNotMaxValue = true;
            private long current = min();

            public boolean hasNext() {
                return current <= max() && isNotMaxValue;
            }

            public Long next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Range.iterator()");
                }
                long value = current;

                if (current != Long.MAX_VALUE) {
                    current++;
                } else {
                    isNotMaxValue = false;
                }

                return value;
            }
        };
    }
}


