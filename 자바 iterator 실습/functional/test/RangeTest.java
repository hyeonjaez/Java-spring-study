package org.example.functional.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.example.functional.Iterators;
import org.example.functional.Range;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class RangeTest {
    @Test
    @DisplayName("open() 테스트 a, b -> (a,b)")
    public void openConstructorTest() {
        long errorStart = -1;
        long errorEnd = 0;
        assertThrows(IllegalArgumentException.class, () -> Range.open(errorStart, errorEnd));
        assertTrue(Range.open(1, 10) instanceof Range);
        Range range = Range.open(Long.MIN_VALUE, Long.MAX_VALUE);
        long expectedMin = Math.addExact(Long.MIN_VALUE, 1);
        long expectedMax = Math.subtractExact(Long.MAX_VALUE, 1);
        assertEquals(range.min(), expectedMin);
        assertEquals(range.max(), expectedMax);
    }


    @Test
    @DisplayName("closed() 테스트 a, b -> [a,b]")
    public void closedConstructorTest() {
        long errorStart = 1;
        long errorEnd = 0;
        assertThrows(IllegalArgumentException.class, () -> Range.closed(errorStart, errorEnd));
        assertTrue(Range.closed(1, 10) instanceof Range);
        Range range = Range.closed(Long.MIN_VALUE, Long.MAX_VALUE);
        long expectedMin = Long.MIN_VALUE;
        long expectedMax = Long.MAX_VALUE;
        assertEquals(range.min(), expectedMin);
        assertEquals(range.max(), expectedMax);
    }

    @Test
    @DisplayName("closedOpen() 테스트 a, b -> [a,b)")
    public void closedOpenConstructorTest() {
        long errorStart = 0;
        long errorEnd = 0;
        assertThrows(IllegalArgumentException.class, () -> Range.closedOpen(errorStart, errorEnd));
        assertTrue(Range.closedOpen(1, 10) instanceof Range);
        Range range = Range.closedOpen(Long.MIN_VALUE, Long.MAX_VALUE);
        long expectedMin = Long.MIN_VALUE;
        long expectedMax = Math.subtractExact(Long.MAX_VALUE, 1);
        assertEquals(range.min(), expectedMin);
        assertEquals(range.max(), expectedMax);
    }

    @Test
    @DisplayName("openClosed() 테스트 a, b -> (a,b]")
    public void openClosedConstructorTest() {
        long errorStart = 0;
        long errorEnd = 0;
        assertThrows(IllegalArgumentException.class, () -> Range.openClosed(errorStart, errorEnd));
        assertTrue(Range.openClosed(1, 10) instanceof Range);
        Range range = Range.openClosed(Long.MIN_VALUE, Long.MAX_VALUE);
        long expectedMin = Math.addExact(Long.MIN_VALUE, 1);
        long expectedMax = Long.MAX_VALUE;
        assertEquals(range.min(), expectedMin);
        assertEquals(range.max(), expectedMax);
    }

    @Test
    @DisplayName("is() 테스트 a -> a")
    public void isConstructorTest() {
        Range rangeMin = Range.is(Long.MIN_VALUE);
        Range rangeMax = Range.is(Long.MAX_VALUE);
        assertTrue(Range.is(10) instanceof Range);
        assertEquals(rangeMax.max(), Long.MAX_VALUE);
        assertEquals(rangeMin.min(), Long.MIN_VALUE);
    }

    @Test
    @DisplayName("fromMinValue() 테스트 a -> [Long.MIN_VALUE, a]")
    public void fromMinValueConstructorTest() {
        long endValue = 0;
        assertEquals(Range.fromMinValue(endValue).min(), Long.MIN_VALUE);
        assertEquals(Range.fromMinValue(endValue).max(), endValue);
        assertTrue(Range.fromMinValue(1) instanceof Range);
        long endMaxValue = Long.MAX_VALUE;
        assertEquals(Range.fromMinValue(endMaxValue).min(), Long.MIN_VALUE);
        assertEquals(Range.fromMinValue(endMaxValue).max(), endMaxValue);

    }

    @Test
    @DisplayName("toMaxValue() 테스트 a -> [a, Long.MAX_VALUE]")
    public void toMaxValueConstructorTest() {
        long startValue = 0;
        assertEquals(Range.toMaxValue(startValue).min(), startValue);
        assertEquals(Range.toMaxValue(startValue).max(), Long.MAX_VALUE);
        assertTrue(Range.toMaxValue(1) instanceof Range);
        long startMaxValue = Long.MIN_VALUE;
        assertEquals(Range.toMaxValue(startMaxValue).min(), startMaxValue);
        assertEquals(Range.toMaxValue(startMaxValue).max(), Long.MAX_VALUE);
    }

    @Test
    @DisplayName("size() 테스트")
    public void size() {
        Range range = Range.closed(1, 10);
        assertTrue(range.size() instanceof BigInteger);
        assertEquals(range.size(), BigInteger.valueOf(10));

        Range range1 = Range.closed(Long.MIN_VALUE, Long.MAX_VALUE);
        BigInteger minValue = BigInteger.valueOf(Long.MIN_VALUE);
        BigInteger maxvalue = BigInteger.valueOf(Long.MAX_VALUE);
        assertEquals(range1.size(), maxvalue.subtract(minValue).add(BigInteger.ONE));
    }


    @Test
    public void iteratorTypeTest() {
        assertTrue(Range.open(1, 10).iterator() instanceof Iterator);
        assertTrue(Range.open(1, 10).iterator().next() instanceof Long);

        assertTrue(Range.closed(1, 10).iterator() instanceof Iterator);
        assertTrue(Range.closed(1, 10).iterator().next() instanceof Long);

        assertTrue(Range.closedOpen(1, 10).iterator() instanceof Iterator);
        assertTrue(Range.closedOpen(1, 10).iterator().next() instanceof Long);

        assertTrue(Range.openClosed(1, 10).iterator() instanceof Iterator);
        assertTrue(Range.openClosed(1, 10).iterator().next() instanceof Long);

        assertTrue(Range.is(1).iterator() instanceof Iterator);
        assertTrue(Range.is(1).iterator().next() instanceof Long);

        assertTrue(Range.fromMinValue(1).iterator() instanceof Iterator);
        assertTrue(Range.fromMinValue(1).iterator().next() instanceof Long);
        assertTrue(Range.fromMinValue(1).iterator().next() instanceof Long);

        assertTrue(Range.toMaxValue(1).iterator() instanceof Iterator);
        assertTrue(Range.toMaxValue(1).iterator().next() instanceof Long);

        assertTrue(Range.all().iterator() instanceof Iterator);
        assertTrue(Range.all().iterator().next() instanceof Long);

    }


    static Stream<Arguments> iteratorData() {
        return Stream.of(
                Arguments.of(Range.open(1, 5).iterator(), Stream.of(2L, 3L, 4L).iterator()),
                Arguments.of(Range.closed(1, 5).iterator(), Stream.of(1L, 2L, 3L, 4L, 5L).iterator()),
                Arguments.of(Range.closedOpen(1, 5).iterator(), Stream.of(1L, 2L, 3L, 4L).iterator()),
                Arguments.of(Range.openClosed(1, 5).iterator(), Stream.of(2L, 3L, 4L, 5L).iterator()),
                Arguments.of(Range.is(1).iterator(), Stream.of(1L).iterator()),
                Arguments.of(Range.fromMinValue(Math.addExact(Long.MIN_VALUE, 2L)).iterator(),
                        Stream.of(Long.MIN_VALUE, Long.MIN_VALUE + 1L, Long.MIN_VALUE + 2L).iterator()),
                Arguments.of(Range.toMaxValue(Long.MAX_VALUE - 1L).iterator(),
                        List.of(Long.MAX_VALUE - 1L, Long.MAX_VALUE).iterator())
        );
    }

    @ParameterizedTest
    @MethodSource("iteratorData")
    @DisplayName("iterator() simple test")
    public void iteratorTest(Iterator<Long> actual, Iterator<Long> expected) {
        assertTrue(Iterators.equals(actual, expected));
    }

}
