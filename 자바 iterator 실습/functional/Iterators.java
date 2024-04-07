package org.example.functional;

import static org.example.Precondition.checkArgument;
import static org.example.Precondition.checkArgumentIsNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Iterable 또는 Iterator 요소를 이진 함수를 하용하여 축소하는 유틸리티 클래스이다.
 * <p>
 * InfiniteIterator를 삭제 한 이유
 * <p>일단 리스코프 치환원칙은 상속 관계에서 sub type이 base type을 대체할 수 있어야 한다는 원칙이다. 이 원칙을 잘 지키면 코드의 유연성과 확장성이 향상된다.
 * InfiniteIterator가 Iterator를 상속받는 것 자체는 일반적으로 문제가 없다고 생각하지만 InfiniteIterator가 Iteratordml Contract을 위반하면 LSP도 위반한다.
 * Iterator는 hasNext() 및 next() 라는 두가지 메서드가 있다. hansNext()는 그다음 요소가 있는지 확인하고 boolean값을 반환하고 next()는 그 값을 반환한다.
 * LSP를 지키려면 InfiniteIterator에서도 이런 특징에 맞게 구현해야한다.</p>
 * InfiniteIterator가 LSP를 위배하는 이유
 *     <ul>
 *         <li>1. 무한성 : Iterator의 특성이 hasNext()를 확인하여 다음 요소가 있는지 여부를 판별하고 있으면 next()로 값을 반환하는 특성이 있는데 서브타입인 InfiniteIterator는 그 특성을 무시하고 무조건 hasNext()는 true를 반환하고 있다. subtype이 basetype의 특성을 무시한다.</li>
 *         <li>2. 예외 처리 : 그리고 Iterator의 next()를 하였을때 만약 값이 없다면 NoSuchException이라는 예외를 던지도록 제약 조건이 있다. 하지만 InfiniteIterator는 무조건 값이 있기 때문에 그런 제약 조건을 무시한다. 한마디로 subtype이 basetype의 제약 조건을 무시하게 된다.</li>
 *     </ul>
 *     나의 생각
 *     <ul>
 *         Iterator는 컬렉션 객체를 반복 할때 사용된다. 컬렉션 내의 요소들을 반복한다. 근데 이 컬렉션 요소들이 무한하다고 하면 이 Iterator도 무한할 것이다. Iterator는 컬렉션의 요소에 따라 무한할지 유한할지가 정해진다고 생각한다. 그럼으로 Iterator자체가 유한할수도 있고 무한할 수도 있다고 생각한다.
 *         하지만 무한하다고 하면 Iterator관련 메서드를 호출할때도 요소들이 무한하기 때문에 해당 메서드도 무한히 동작할것이다. 이런 문제점 때문에 이 클래스에서는 무한한 Iterator를 사용할때 요소 갯수를 제한해주는 메서드를 같이 호출하는 것을 권장하고 있다.
 *     </ul>
 * </p>
 */
public class Iterators<T> {

    /**
     * Iterable의 각 요소를 이진 함수를 사용하여 감소시키고 초기값을 시작하여 축소된 결과 값을 반환한다
     *
     * @param <E>        Iterable의 요소 타입
     * @param <R>        결과 타입
     * @param es         축소할 요소들의 Iterable
     * @param biFunction 각 요소에 적용할 이진 함수
     * @param init       초기 값
     * @return 축소 결과
     * @throws IllegalArgumentException {@code es}  null 인 경우
     * @throws IllegalArgumentException {@code biFunction} null 인 경우
     */
    public static <E, R> R reduce(Iterable<E> es, BiFunction<R, E, R> biFunction, R init) {
        checkArgumentIsNull(es);
        checkArgumentIsNull(biFunction);
        R result = init;
        for (E e : es) {
            result = biFunction.apply(result, e);
        }
        return result;
    }


    /**
     * Iterator를 이용하여 초기값 부터 시작해서 이진 함수를 이용하여 감소 연산을 수행한다.
     *
     * @param es         소모할 Iterator
     * @param biFunction 각각의 요소와 축소되고 있는 결과에 적용할 이진 함수
     * @param init       초기값
     * @param <E>        Iterator의 요소 타입
     * @param <R>        결과의 타입
     * @return Iterator의 각 요소에 이진 함수를 적용한 후 최종 감소 연산의 결과
     * @throws IllegalArgumentException {@code es} null 인 경우
     * @throws IllegalArgumentException {@code biFunction} null 인 경우
     */
    public static <E, R> R reduce(Iterator<E> es, BiFunction<R, E, R> biFunction, R init) {
        checkArgumentIsNull(es);
        checkArgumentIsNull(biFunction);
        return reduce(() -> es, biFunction, init);
    }


    /**
     * 두개의 Iterator를 받아서 각 위치에 대해 요소를 비교하고 두 Iterator가 같은지 판단 하는 메서드이다.
     * 두개의 Iterator를 받아서 zip()이라는 메서드를 이용하여 동시에 두 Iterator의 요소들을 순차적으로 zip를하고 Iterator를 반환한다. 그리고 reduce()를 호출하여 zip한 Iterator를 소모하며 값을 비교해주고 동작이 다 끝나면 처음 두 iterator가 각각 요소들을 모두 다 소모 했는지도 검사를 해준다.
     * <p>
     * "equals" 메소드는 두 Iterator에 대한 등가 관계를 구현한다.
     * "equals" 메소드에 대한 규칙이다.
     * <ul>
     * <li>1. 비 반사성 : Iterator 변수 x에 대해 Iterator의 기본 특성으로 인한 equals(x, x)는 false가 반환될 수 있다.</li>
     * <li>2. 대칭성 : 두 Iterator x와 y에 대해 equals(x, y)가 true 이면 (y, x)도 true여야 한다.</li>
     * <li>3. 추이성 : Iterator 참조 값 x,y,z에 대해 equals(x, y)가 true이고 equals(y, z)도 true이면 equals(x, z)도 true여야 한다.</li>
     * <li>4. 소모성 : 두 Iterator를 비교하기 위해 equals(x, y)를 하게 된다면 두 Iterator는 각자의 값을 모두 다 소모 해야 한다.</li>
     * </ul>
     * <p>
     * "equals" 메소드에 대한 주의할 점
     * <li>이 메서드의 특징이 Iterator를 모두 소모하여 최종 결과를 반환하게 하기 때문에 무한한 Iterator를 비교하게 된다면 끝나지 않을 것이다.</li>
     * 이러한 이유로 무한한 Iterator 비교는 권장하지 않는다.
     *
     * @param xs  비교할 첫번째 Iterator
     * @param ys  비교할 두번째 Iterator
     * @param <T> 비교 대상 Iteratordml 요소 타입
     * @return 두 Iterator가 각각의 요소와 순서가 모두 동일하면 true, 그렇지 않으면 false
     * @throws IllegalArgumentException {@code xs} null인 경우, {@code ys} null인 경우
     */
    public static <T> boolean equals(Iterator<T> xs, Iterator<T> ys) {
        checkArgumentIsNull(xs);
        checkArgumentIsNull(ys);

        return reduce(zip(Objects::equals, xs, ys), (x, y) -> x && y, true) && (xs.hasNext() == ys.hasNext());
        // xs.hasNext() && ys.hasNext(); 이걸 해주는 이유 : zip은 길이가 다르면 더 짧은 쪽의 요소만을 고려한다
        //zip은 Iterator 중 하나가 다른 하나보다 더 길다면 나머지 요소를 무시하게 된다.
        // 이러한 이유로 xs.hasNext() == ys.hasNext() 구문을 추가하여 동일한 길이인 경우에만 true를 반환하도록 해야한다.
    }


    /**
     * Iterator의 요소들을 소모하여 각각의 요소 마다 구분자를 넣어서 연결하고 그 문자열을 반환합니다.
     * <p>
     * "toString" 메소드는 Iterator의 모든 요소들을 소모하여 문자열 표현을 반환한다.
     * <p>
     * "toString" 메소드에 대한 주의할 점
     * <li>이 메서드의 특징이 Iterator를 모두 소모하고 최종 결과를 반환하기 때문에 무한한 Iterator를 넣게 된다면 끝나지 않을 것이다.</li>
     * 이러한 이유로 무한한 Iterator를 권장하지 않는다.
     * </p></p>
     *
     * @param <T>       Iterator의 요소 타입
     * @param es        문자열로 변환할 Iterator
     * @param separator 각 요소 사이에 삽입할 구분자 문자열
     * @return Iterator의 모든 요소와 구분자를 연결한 문자열
     * @throws IllegalArgumentException {@code es} null인 경우
     * @throws IllegalArgumentException {@code separator} null인 경우
     */
    public static <T> String toString(Iterator<T> es, String separator) {
        checkArgumentIsNull(es);
        checkArgumentIsNull(separator);
        StringBuilder sb = new StringBuilder();
        if (es.hasNext()) {
            sb.append(es.next());
        }

        return reduce(es, (o, t) -> o.append(separator).append(t), sb).toString();
    }


    /**
     * Iterator의 각 요소에 지정된 함수를 적용하여 새로운 Iterator를 반환합니다.
     * <p>
     * "map" 메소드에 대한 주의할 점
     * <li>이 메서드에 무한한 iterator를 넣게 된다면 무한한 Iterator에 대한 map 연산을 수행하면서 끝없이 계속된 결과를 생성하려고 하게 될것입니다.</li>
     * <li>이러한 이유로 무한한 Iterator를 권장하지 않습니다.</li>
     * </p>
     *
     * @param <E>      입력 Iterator의 요소 타입
     * @param <R>      새로운 Iterator의 요소 타입
     * @param es       함수를 적용할 입력 Iterator
     * @param function 각 입력 요소에 적용할 함수
     * @return 함수가 적용된 새로운 Iterator
     * @throws IllegalArgumentException {@code es} null인 경우
     * @throws IllegalArgumentException {@code function} null인 경우
     */
    public static <E, R> Iterator<R> map(Iterator<E> es, Function<E, R> function) {
        checkArgumentIsNull(es);
        checkArgumentIsNull(function);
        return new Iterator<R>() {
            public boolean hasNext() {
                return es.hasNext();
            }

            public R next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("map()");
                }
                return function.apply(es.next());
            }
        };
    }

    /**
     * Iterator의 각 요소를 주어진 조건에 따라 필터링하여 새로운 Iterator를 반환합니다.
     * <p>
     * "filter" 메소드에 대한 주의할 점
     * <li>이 메서드에 무한한 Iterator를 넣게 된다면 filter는 무한한 조건을 검사하면서 해당 조건을 만족하는 요소를 찾으려고 시도할 것이다.</li>
     * <li>이러한 이유로 무한한 Iterator를 권장하지 않는다.</li>
     * </p>
     *
     * @param <E>       입력 Iterator의 요소 타입
     * @param iterator  필터링할 입력 Iterator
     * @param predicate 각 요소에 적용할 조건을 정의하는 Predicate
     * @return 조건에 맞는 요소만 포함된 새로운 Iterator
     * @throws IllegalArgumentException {@code iterator} null인 경우
     * @throws IllegalArgumentException {@code predicate} null인 경우
     */
    public static <E> Iterator<E> filter(Iterator<E> iterator, Predicate<E> predicate) {
        checkArgumentIsNull(iterator);
        checkArgumentIsNull(predicate);

        return new Iterator<E>() {
            private E current = findFirst(iterator, predicate);

            public boolean hasNext() {
                return !Objects.isNull(current);
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("filter");
                }
                E prev = current;
                current = findFirst(iterator, predicate);
                return prev;
            }
        };
    }

    /**
     * Iterator에서 주어진 조건을 만족하는 첫 번째 요소를 찾아 반환합니다. 조건을 만족하는 요소가 없으면 null을 반환합니다.
     *
     * @param <E>       Iterator의 요소 유형
     * @param iterator  찾아볼 Iterator
     * @param predicate 각 요소에 적용할 조건을 정의하는 Predicate
     * @return 조건을 만족하는 첫 번째 요소, 찾지 못할 경우에는 null 반환
     * @throws IllegalArgumentException {@code iterator} null 인 경우
     * @throws IllegalArgumentException {@code predicate} null 인 경우
     */
    public static <E> E findFirst(Iterator<E> iterator, Predicate<E> predicate) {
        checkArgumentIsNull(iterator);
        checkArgumentIsNull(predicate);

        while (iterator.hasNext()) {
            E first = iterator.next();
            if (predicate.test(first)) {
                return first;
            }
        }
        return null;
    }

    /**
     * 주어진 시드 값을 시작으로 UnaryOperator를 반복적으로 적용하여 요소를 생사ㅓㅇ하는 무한한 Iterator를 반환한다.
     * hasNext() 메서드는 항상 true를 반환하고 next() 호출시 주어진 UnaryOperator를 이용하여 각각의 요소에 적용한다.
     * <p>
     * "iterate" 권장하는 사용법
     * <li>이 메서드는 hasNext()에서 true를 반환하고 있다. 즉 무한한 요소 값들이 있다는 거다.</li>
     * <li>이러한 이유로 요소의 갯수를 제한하는 메서드와 같이 사용하는 것을 권장한다.</li>
     * </p>
     *
     * @param <T>  Iterator의 요소 타입
     * @param seed 시작 시드 값
     * @param f    각 요소에 적용할 UnaryOperator
     * @return 주어진 시드 값을 시작으로 생성된 무한 Iterator
     * @throws IllegalArgumentException {@code f} null인 경우
     */
    public static <T> Iterator<T> iterate(T seed, UnaryOperator<T> f) {
        checkArgumentIsNull(f);
        return new Iterator<T>() {
            T current = seed;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                T old = current;
                current = f.apply(current);
                return old;
            }
        };
    }

    /**
     * Iterator에서 요소의 갯수를 제한하여 새로운 Iterator를 반환한다.
     * <p>
     * "limit" 메서드의 규칙
     * <li>최대 기수 제한 : 파라미터인 maxSize는 반환되는 Iterator에서 최대로 반환될 요소의 개수를 가져온다</li>
     * <li>maxsize가 iterator의 요소 갯수보다 클때 limit이라는 메서드가 의미하는게 갯수 제한이지 maxSize만큼의 갯수를 단정짓는게 아니라서 iterator의 요소 갯수가 maxSize의 크기보다 적아도 정상 작동이 된다.</li>
     * </p>
     *
     * @param <T>      Iterator의 요소 타입
     * @param iterator 제한할 입력 Iterator
     * @param maxSize  최대 요소 개수
     * @return 최대 maxSize만큼의 요소의 갯수를 제한한 새로운 Iterator
     * @throws IllegalArgumentException
     */
    public static <T> Iterator<T> limit(Iterator<T> iterator, long maxSize) {
        checkArgumentIsNull(iterator);
        checkArgument(x -> x < 0, maxSize);
        if (maxSize < 0) {
            throw new IllegalArgumentException("maxSize < 0");
        }

        return new Iterator<T>() {
            long currentCount = 0;

            @Override
            public boolean hasNext() {
                return currentCount < maxSize && iterator.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("no such");
                }
                currentCount = Math.addExact(currentCount, 1);
                return iterator.next();
            }
        };
    }

    /**
     * Supplier로 부터 무한한 요소를 생성하는 무한한 Iterator를 반환한다.
     * hasNext() 메서드는 항상 true를 반환하고 next() 메서드 호출시 주어진 Supplier를 통해 새로운 요소를 생성한다.
     * <p>
     * "generate" 권장하는 사용법
     * <li>이 메서드는 hasNext()에서 true를 반환하고 있다. 즉 무한한 요소 값들이 있다는 거다.</li>
     * <li>이러한 이유로 요소의 갯수를 제한하는 메서드와 같이 사용하는 것을 권장한다.</li>
     * </p>
     *
     * @param supplier 생성에 사용할 Supplier
     * @param <T>      Iterator의 요소 타입
     * @return Supplier로 부터 생성된 값을 반환하는 Iterator
     * @throws IllegalArgumentException Supplier가 null 인 경우
     */


    public static <T> Iterator<T> generate(Supplier<T> supplier) {
        checkArgumentIsNull(supplier);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return supplier.get();
            }
        };

    }

    /**
     * 두개의 Iterator에서 요소를 가져와서 BiFunction을 사용하여 새로운 요소를 생성하는 Iterator를 반환한다.
     *
     * @param biFunction 각 요소에 적용할 BiFunction
     * @param xIterator  첫 번째 Iterator
     * @param yIterator  두 번째 Iterator
     * @param <X>        첫번째 Iterator의 요소 타입
     * @param <Y>        두번째 Iterator의 요소 타입
     * @param <Z>        두 Iterator에서 가져온 요소를 BiFunction을 이용하여 생성된 값의 유형
     * @return BiFunction을 사용하여 생성된 값을 반환하는 Iterator
     * @throws IllegalArgumentException {@code biFunction} null 인 경우
     * @throws IllegalArgumentException {@code xIterator} null 인 경우
     * @throws IllegalArgumentException {@code yIterator} null 인 경우
     */
    public static <X, Y, Z> Iterator<Z> zip(BiFunction<X, Y, Z> biFunction, Iterator<X> xIterator,
                                            Iterator<Y> yIterator) {
        checkArgumentIsNull(biFunction);
        checkArgumentIsNull(xIterator);
        checkArgumentIsNull(yIterator);

        return new Iterator<Z>() {
            public boolean hasNext() {
                return xIterator.hasNext() && yIterator.hasNext();
            }

            public Z next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("zip");
                }
                return biFunction.apply(xIterator.next(), yIterator.next());
            }
        };
    }

    /**
     * Iterator의 요소의 총 갯수를 세어 반환한다.
     *
     * @param iterator 갯수를 셀 요소를 포함하는 Iterator
     * @param <E>      Iterator의 요소 타입
     * @return Iterator의 요소 총 갯수
     * @throws IllegalArgumentException {@code iterator} null 인 경우
     */
    public static <E> long count(Iterator<E> iterator) {
        checkArgumentIsNull(iterator);
        return reduce(iterator, (o, e) -> Math.addExact(o, 1), 0);
    }

    /**
     * Iterator에서 지정된 인덱스의 요소를 반환한다.
     *
     * @param iterator 가져올 요소를 포함하는 Iterator
     * @param index    반환할 요소의 인덱스(0보다 커야한다)
     * @param <T>      Iterator의 요소 타입
     * @return 인덱스의 요소
     * @throws IllegalArgumentException  {@code iterator} null 인 경우
     * @throws IndexOutOfBoundsException {@code index} index가 0 보다 작은 경우
     */

    public static <T> T get(Iterator<T> iterator, long index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < " + index);
        }
        checkArgumentIsNull(iterator);
        return getLast(limit(iterator, index + 1));
    }


    /**
     * Iterator에서 마지막에 위치하는 요소를 가져온다.
     *
     * @param iterator 가져올 요소를 포함하는 Iterator
     * @param <T>      Iterator의 요소 타입
     * @return Iterator의 마지막 요소
     */
    private static <T> T getLast(Iterator<T> iterator) {
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    /**
     * Iterator의 요소들을 포함하는 List를 생성하여 반환한다.
     *
     * @param iterator List로 변환할 Iterator
     * @param <T>      Iterator의 요소 타입
     * @return Iterator의 요소들을 포함하는 List
     * @throws IllegalArgumentException {@code iterator} null 인 경우
     */
    public static <T> List<T> toList(Iterator<T> iterator) {
        checkArgumentIsNull(iterator);
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> void print(Iterator<E> iterator, String separator, java.io.PrintStream printStream) {
        printStream.print(toString(iterator, separator));
    }

    public static <E> void print(Iterator<E> iterator, String separator) {
        print(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator, String separator, java.io.PrintStream printStream) {
        print(iterator, separator, printStream);
        printStream.println();
    }

    public static <E> void println(Iterator<E> iterator, String separator) {
        println(iterator, separator, System.out);
    }

    public static <E> void println(Iterator<E> iterator) {
        println(iterator, ", ");
    }

    private Iterators() {
    }


}


