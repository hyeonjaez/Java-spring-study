package org.example;

import static org.example.Precondition.checkArgument;
import static org.example.Precondition.checkArgumentIsNull;
import static org.example.functional.Iterators.generate;
import static org.example.functional.Iterators.reduce;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import org.example.functional.Fibonacci;
import org.example.functional.Range;

/**
 * 클래스 Mathx는 수학 관련하여 좀 더 확장된 숫자 연산을 수행하는 메서드가 포함되어 있다.
 */
public class Mathx {
    private Mathx() {
    }

    /**
     * 무작위 정수를 생성하여 반환한다.
     *
     * @return 생성된 무작위 정수
     */
    public static int randInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    /**
     * 주어진 범위 내에서 무작위 정수를 생성 하여 반환 한다.
     *
     * @param origin         시작 값 (포함)
     * @param boundExclusive 종료 값 (제외)
     * @return 주어진 범위 내에서 생성된 무작위 정수
     * @throws IllegalArgumentException 시작 값이 종료 값 보다 클때 발생
     */
    public static int randInt(int origin, int boundExclusive) {
        if (origin > boundExclusive) {
            throw new IllegalArgumentException("시작 값이 종료 값 보다 크다");
        }
        return ThreadLocalRandom.current().nextInt(origin, boundExclusive);
    }

    /**
     * 0.0 부터 1.0 사이의 무작위 부동 소수점 수를 생성하여 반환 한다.
     *
     * @return 생성된 무작위 부동 소수점 숫자
     */
    public static double randDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    /**
     * 무작위 부동 소수점 숫자를 생성 하는 무한한 Iterator를 반환 한다.
     *
     * @return 무작위 부동 소수점 숫자를 생성 하는 무한한 Iterator
     */
    public static Iterator<Double> randDoubles() {
        return generate(Mathx::randDouble);
    }

    /**
     * 무작위 정수 숫자를 생성 하는 무한한 Iterator를 반환 한다.
     *
     * @return 무작위 정수 숫자를 생성 하는 무한한 Iterator
     */
    public static Iterator<Integer> randInts() {
        return generate(Mathx::randInt);
    }

    /**
     * 주어진 Iterable의 요소들을 소모하여 숫자 합을 계산하여 반환한다.
     * 주어진 숫자들은 Number의 서브 타입이여야 한다.
     *
     * @param numbers 숫자들이 있는 Iterable
     * @param <T>     Number의 서브타입
     * @return 숫자들의 합
     * @throws IllegalArgumentException {@code numbers}가 null인 경우
     */
    public static <T extends Number> double sum(Iterable<T> numbers) {
        checkArgumentIsNull(numbers);
        double result = reduce(numbers, (x, y) -> x.doubleValue() + y.doubleValue(), 0D);
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Infinite result in sum");
        }

        return result;
    }

    /**
     * 주어진 Iterator의 요소들을 소모하여 각 요소의 숫자 합을 계산하여 반환한다.
     * 주어진 Iterator의 요소들은 Number의 서브 타입이여야 한다.
     *
     * @param numbers 숫자들이 있는 Iterator
     * @param <T>     Number의 서브타입
     * @return 숫자들의 합
     * @throws IllegalArgumentException {@code numbers}가 null인 경우
     */
    public static <T extends Number> double sum(Iterator<T> numbers) {
        checkArgumentIsNull(numbers);
        double result = sum(() -> numbers);
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Infinite result in sum");
        }
        return result;
    }

    /**
     * 주어진 Range에서의 구간안의 모든 숫자들의 합을 계산하여 반환한다.
     * 구간의 최댓값과 최솟값을 사용하여 등차수열의 합을 계산하고
     * 산술 연산시 오버플로우에 대한 예외를 처리한다.
     *
     * @param range 숫자의 구간을 나타낸다.
     * @return 주어진 구간 내의 숫자의 합
     * @throws IllegalArgumentException {@code range}가 null인 경우
     * @throws ArithmeticException      산술 연산 시 오버플로우가 발생한 경우 발생
     */
    public static long sum(Range range) {
        checkArgumentIsNull(range);
        final long max = range.max();
        final long min = range.min();

        return Math.multiplyExact(range.size().longValueExact(), Math.addExact(max, min)) / 2L;
    }

    /**
     * Iterator에서 각 요소의 숫자의 곱을 계산하여 반환한다.
     * Iterator의 요소는 Number의 서브 타입이여야 한다.
     *
     * @param numbers 숫자들의 Iterator
     * @param <T>     숫자들의 타입 Number의 서브타입
     * @return Iterator의 요소들의 곱
     * @throws IllegalArgumentException {@code numbers}가 null인 경우
     */
    public static <T extends Number> double product(Iterator<T> numbers) {
        checkArgumentIsNull(numbers);
        double result = product(() -> numbers);
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Infinite result in product");
        }
        return result;
    }

    /**
     * Iterable에서 각 요소의 숫자의 곱을 계산하여 반환한다.
     * Iterable의 요소는 Number의 서브 타입이여야 한다.
     *
     * @param numbers 숫자들의 Iterable
     * @param <T>     숫자들의 타입 Number의 서브타입
     * @return Iterable의 요소들의 곱
     * @throws IllegalArgumentException {@code numbers}가 null인 경우
     */
    public static <T extends Number> double product(Iterable<T> numbers) {
        checkArgumentIsNull(numbers);
        double result = reduce(numbers, (x, y) -> x.doubleValue() * y.doubleValue(), 1D);
        if (Double.isInfinite(result)) {
            throw new ArithmeticException("Infinite result in product");
        }
        return result;
    }

    /**
     * Range에서 제공된 구간안의 숫자의 곱을 계산하여 반환한다.
     * 구간의 최댓값과 최솟값을 사용하여 등차수열의 곱을 계산하고 산술 연산 시 오버플로우에 대한 예외를 처리한다.
     *
     * @param range 숫자의 구간을 나타내는 Range
     * @return 구간내의 숫자의 곱
     * @throws IllegalArgumentException {@code range}가 null인 경우
     * @throws ArithmeticException      산술 연산 시 오버플로우가 발생한 경우 발생
     */
//    public static long product(Range range) {
//        checkArgumentIsNull(range);
//        return reduce(range, Math::multiplyExact, 1L);
//    }
    public static BigInteger product(Range range) {
        checkArgumentIsNull(range);
        return reduce(range, (x, y) -> x.multiply(BigInteger.valueOf(y)), BigInteger.ONE);
    }

    /**
     * 두 숫자의 최대 공약수를 계산하여 반환한다.
     * BigInteger 클래스의 gcd 메서드를 사용하여 최대 공약수를 계산한다.
     *
     * @param x 첫 번째 숫자
     * @param y 두 번째 숫자
     * @return 두 숫자의 최대 공약수
     */
    public static long gcd(long x, long y) {
        if (x == 0 || y == 0) {
            throw new IllegalArgumentException("");
        }
        return BigInteger.valueOf(x).gcd(BigInteger.valueOf(y)).longValueExact();
    }

    /**
     * randInt() 메서드를 사용하여 생성된 두 무작위 수의 최대 공약수가 1인지 확인하는 메서드이다
     *
     * @return 두 무작위 수의 최대 공약수가 1이면 true 아니면 false
     */
    public static boolean dirichletTest() {
        return gcd(randInt(), randInt()) == 1;
    }

    /**
     * 주어진 범위 내에서 균등 분포에 따른 정수 난수를 생성하는 Iterator를 반환한다.
     *
     * @param origin         난수의 최솟값
     * @param boundInclusive 난수의 최댓값
     * @return 주어진 범위 내에서 생성된 균등 분포에 따른 정수 난수를 생성하는 Iterator
     * @throws IllegalArgumentException {@code origin}이 음수 일 경우
     * @throws IllegalArgumentException {@code origin}이 {@code boundInclusive} 보다 큰 경우
     */
    public static Iterator<Integer> discreteUniformDistribution(int origin,
                                                                int boundInclusive) {
        checkArgument(x -> x < 0, origin);

        if (origin > boundInclusive) {
            throw new IllegalArgumentException("");
        }
        return generate(() -> randInt(origin - 1, boundInclusive) + 1);
    }

    /**
     * 0부터 주어진 boundInculsive 까지의 범위에서 난수를 생성하는 Iterator를 반환한다.
     *
     * @param boundInclusive boundInclusive 생성될 난수의 상한값 (포함)
     * @return 0부터 boundInculsive 까지의 난수를 생성하는 Iterator
     * @throws IllegalArgumentException boundInclusive가 음수인 경우 발생
     */
    public static Iterator<Integer> discreteUniformDistribution(int boundInclusive) {
        checkArgument(x -> x < 0, boundInclusive);

        return generate(() -> randInt(0, boundInclusive + 1));
    }

    /**
     * 주어진 enum 타입에 대해 균일하게 분포된 난수를 생성하여 반환한다.
     * enum형 상수의 개수에 따라 0부터 개수까지의 난수를 생성한다.
     *
     * @param enumType 균일하게 분포된 난수를 생성할 enum 타입
     * @param <T>      enum 타입
     * @return enum 상수에 대해 균일하게 분포된 난수를 생성한다.
     */
    public static <T> int randEnumUniformlyDistributed(Class<T> enumType) {
        T[] constants = enumType.getEnumConstants();
        return randInt(0, constants.length);
    }

    /**
     * 주어진 enum타입에 대해 균일하게 분포된 난수를 생성하는 Iterator를 반환한다.
     * enum형 상수의 개수에 따라 0부터 개수까지의 난수를 생성한다.
     *
     * @param enumType 균일하게 분포된 난수를 생성할 enum 타입
     * @param <T>      enum 타입
     * @return enum 상수에 대해 균일하게 분포된 난수르 생성한다.
     */
    public static <T extends Enum<T>> Iterator<Integer> discreteUniformDistribution(
            Class<T> enumType) {
        return generate(() -> randEnumUniformlyDistributed(enumType));
    }

    /**
     * 정규 분포에 따른 난수를 생성하고 반환한다.
     *
     * @param mean              정규 분포의 평균값
     * @param standardDeviation 정규 분포의 표준 편차
     * @return 생성된 정규 분포에 따른 난수를 반환한다.
     * @throws IllegalArgumentException {@code standardDeviation}이 음수인 경우 발생
     */
    public static double randDoubleNormallyDistributed(double mean, double standardDeviation) {
        checkArgument(x -> x < 0, standardDeviation);

        return ThreadLocalRandom.current().nextGaussian() * standardDeviation + mean;
    }


    /**
     * 정규 분포에 따른 난수를 생성하는 Iterator를 반환한다.
     *
     * @param mean              정규 분포의 평균값
     * @param standardDeviation 정규 분포의 표준 편차
     * @return 정규 분포에 따른 난수를 생성하는 Iterator
     * @throws IllegalArgumentException {@code standardDeviation}이 음수인 경우 발생ㅣ
     */
    public static Iterator<Double> normalDistribution(double mean,
                                                      double standardDeviation) {
        checkArgument(x -> x < 0, standardDeviation);
        return generate(() -> randDoubleNormallyDistributed(mean, standardDeviation));
    }

    // Bernoulli distribition

    /**
     * 베르누이 분포란 두가지의 가능한 결과만을 갖고 여러 차례 반복하여 단일한 베르누이 시행의 결과를 나타내는 이산 확률 분포이다.
     * 이 메서드에서는 0, 1의 두가지 결과값을 무작위로 생성하는 Iterator를 반환한다.
     *
     * @param probability 0과 1 사이의 확률값
     * @return 베르누이 분포에 따른 이진 난수를 생성하는 Iterator를 반환
     * @throws IllegalArgumentException {@code probability} 가 0 미만 또는 1을 초과하는 경우 발생
     */
    public static Iterator<Integer> binaryDistribution(double probability) {
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("Out of range with " + probability);
        }
        return generate(() -> randDouble() <= probability ? 1 : 0);
    }

    public static Iterator<BigInteger> fibonacci() {
        return new Fibonacci();
    }
}
