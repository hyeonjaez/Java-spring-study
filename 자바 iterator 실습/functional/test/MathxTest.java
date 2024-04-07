package org.example.functional.test;

import static org.example.Mathx.binaryDistribution;
import static org.example.Mathx.discreteUniformDistribution;
import static org.example.Mathx.fibonacci;
import static org.example.Mathx.gcd;
import static org.example.Mathx.normalDistribution;
import static org.example.Mathx.product;
import static org.example.Mathx.randDoubles;
import static org.example.Mathx.randInt;
import static org.example.Mathx.randInts;
import static org.example.Mathx.sum;
import static org.example.functional.Iterators.limit;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import org.example.Mathx;
import org.example.functional.Iterators;
import org.example.functional.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MathxTest {

    @Test
    public void randIntTest() {
        int origin = 2;
        int boundExclusive = 1;
        assertThrows(IllegalArgumentException.class, () -> randInt(origin, boundExclusive));
    }

    @Test
    public void randDoublesTest() {
        assertTrue(randDoubles() instanceof Iterator);
        assertTrue(randDoubles().next() instanceof Double);
    }

    @Test
    public void randIntsTest() {
        assertTrue(randInts() instanceof Iterator);
        assertTrue(randInts().next() instanceof Integer);
    }

    @Test
    public void sumIterableTest() {
        Iterable<Number> numbers = null;
        assertThrows(IllegalArgumentException.class, () -> sum(numbers));
    }

    @Test
    public void sumIterableSimpleTest() {
        List<Integer> list = List.of(3, 6);
        double expected = 9;
        double actual = sum(list);
        assertEquals(expected, actual);
    }

    @Test
    public void sumIterableEmptyTest() {
        List<Integer> list = Collections.emptyList();
        double expected = 0;
        double actual = sum(list);
        assertEquals(expected, actual);
    }

    @Test
    public void sumIteratorTest() {
        Iterator<Integer> numbers = null;
        assertThrows(IllegalArgumentException.class, () -> sum(numbers));
    }

    @Test
    public void sumIteratorSimpleTest() {
        Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();
        double expected = 15;
        double actual = sum(iterator);
        assertEquals(expected, actual);
    }

    @Test
    public void sumIteratorEmptyTest() {
        Iterator<Integer> iterator = Collections.emptyIterator();
        double expected = 0;
        double actual = sum(iterator);
        assertEquals(expected, actual);
    }

    @Test
    public void sumInfiniteTest() {
        List<Double> list = List.of(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> sum(list));
        assertThrows(ArithmeticException.class, () -> sum(List.of(Double.MAX_VALUE, Double.MAX_VALUE).iterator()));
    }

    @Test
    public void sumRangeTest() {
        Range range = null;
        assertThrows(IllegalArgumentException.class, () -> sum(range));
    }

    @Test
    public void sumRangeSimpleTest() {
        Range range = Range.closed(1, 5);
        long expected = 15;
        long actual = sum(range);
        assertEquals(expected, actual);
    }

    @Test
    public void sumTest() {
        assertEquals(sum(Arrays.asList()), IntStream.of().sum());
        assertEquals(sum(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
                IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).sum());
        assertEquals(sum(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator()),
                sum(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator()));
    }

    @Test
    public void productPreconditionTest() {
        Iterable<Integer> iterable = null;
        Iterator<Integer> iterator = null;
        Range range = null;

        assertThrows(IllegalArgumentException.class, () -> product(iterable));
        assertThrows(IllegalArgumentException.class, () -> product(iterator));
        assertThrows(IllegalArgumentException.class, () -> product(range));
    }

    @Test
    public void productSimpleTest() {
        List<Integer> iterable = List.of(1, 2, 3);
        Iterator<Integer> iterator = List.of(1, 2, 3).iterator();
        Range range = Range.closed(1, 3);

        long expected = 6;

        assertEquals(expected, product(iterable));
        assertEquals(expected, product(iterator));
        assertEquals(BigInteger.valueOf(expected), product(range));
    }

    @Test
    public void productEmptyTest() {
        List<Integer> iterable = Collections.emptyList();
        Iterator<Integer> iterator = Collections.emptyIterator();
        Range range = Range.closed(0, 0);

        long expected = 0;

        assertEquals(expected, sum(iterable));
        assertEquals(expected, sum(iterator));
        assertEquals(expected, sum(range));
    }

    @Test
    public void productInfiniteTest() {
        List<Double> list = List.of(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        assertThrows(ArithmeticException.class, () -> product(list));
        assertThrows(ArithmeticException.class, () -> product(List.of(Double.MAX_VALUE, Double.MAX_VALUE).iterator()));
    }

    @Test
    public void productTest() {
        assertEquals(product(Arrays.asList()), IntStream.of().reduce(1, (x, y) -> x * y));
        assertEquals(product(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
                IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).reduce(1, (x, y) -> x * y));
        assertEquals(product(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator()),
                product(IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).iterator()));
    }

    @Test
    public void gcdTest() {
        assertThrows(IllegalArgumentException.class, () -> gcd(0, 2));
        assertThrows(IllegalArgumentException.class, () -> gcd(3, 0));
        assertThrows(IllegalArgumentException.class, () -> gcd(0, 0));
    }

    @Test
    public void gcdSimpleTest() {
        assertEquals(1, gcd(1, 1));
        assertEquals(2, gcd(2, 8));
        assertEquals(4, gcd(-4, -8));
        assertEquals(3, gcd(-3, 9));
        assertEquals(7, gcd(7, -14));
    }

    @Test
    public void discreteUniformDistributionPreconditionTest() {
        assertThrows(IllegalArgumentException.class, () -> discreteUniformDistribution(-1, 3));
        assertThrows(IllegalArgumentException.class, () -> discreteUniformDistribution(3, 1));
        assertThrows(IllegalArgumentException.class, () -> discreteUniformDistribution(-1));
    }

    @Test
    public void discreteUniformDistributionSimpleTest() {
        assertTrue(discreteUniformDistribution(1, 3) instanceof Iterator);
        assertTrue(discreteUniformDistribution(1) instanceof Iterator);
    }

    @Test
    public void normalDistributionPreconditionTest() {
        double mean = 3;
        double errorStandardDeviation = -1;
        assertThrows(IllegalArgumentException.class, () -> normalDistribution(mean, errorStandardDeviation));
    }

    @Test
    public void normalDistributionSimpleTest() {
        assertTrue(normalDistribution(0, 3) instanceof Iterator);
    }

    @Test
    public void binaryDistributionPreconditionTest() {
        assertThrows(IllegalArgumentException.class, () -> binaryDistribution(-1));
        assertThrows(IllegalArgumentException.class, () -> binaryDistribution(2));
    }

    @Test
    public void binaryDistributionSimpleTest() {
        assertTrue(binaryDistribution(0) instanceof Iterator);
        assertTrue(binaryDistribution(1) instanceof Iterator);
    }

    @Test
    @DisplayName("fibonacci test")
    public void fibonacciTest() {
        assertTrue(fibonacci() instanceof Iterator);
        assertTrue(fibonacci().next() instanceof BigInteger);
        Iterable<BigInteger> fib = Mathx::fibonacci;
        assertTrue(Iterators.equals(limit(fibonacci(), 10),
                StreamSupport.stream(fib.spliterator(), false).limit(10).iterator()));
    }


    public static void randDoublesDemo() {
        assertTrue(Iterators.generate(Mathx::randDouble) instanceof Iterator);
        assertTrue(Iterators.generate(Mathx::randInt) instanceof Iterator);
        assertTrue(randDoubles() instanceof Iterator);
        assertTrue(randInts() instanceof Iterator);

        Iterators.println(limit(Iterators.generate(Mathx::randDouble), 20));
        Iterators.println(limit(randDoubles(), 20));
        Iterators.println(new Random().doubles(20).iterator());
        Iterators.println(ThreadLocalRandom.current().doubles(20).iterator());
        Iterators.println(DoubleStream.generate(ThreadLocalRandom.current()::nextDouble).limit(20)
                .iterator());
    }

    public void distributionTest() {
        assertTrue(binaryDistribution(0.5) instanceof Iterator);
        assertTrue(discreteUniformDistribution(1, 4) instanceof Iterator);
        assertTrue(normalDistribution(90, 10) instanceof Iterator);
    }


//    public static void main(String[] args) {
//        randDoublesDemo();
//    }
}
