package org.example.functional.test;

import static org.example.functional.Iterators.count;
import static org.example.functional.Iterators.filter;
import static org.example.functional.Iterators.findFirst;
import static org.example.functional.Iterators.generate;
import static org.example.functional.Iterators.get;
import static org.example.functional.Iterators.iterate;
import static org.example.functional.Iterators.limit;
import static org.example.functional.Iterators.map;
import static org.example.functional.Iterators.reduce;
import static org.example.functional.Iterators.toList;
import static org.example.functional.Iterators.zip;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.example.functional.Iterators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.opentest4j.AssertionFailedError;


public class IteratorsTest {

    @Test
    @DisplayName("Iterable reduce() precondition test")
    public void reduceIterableTest() {
        assertThrows(IllegalArgumentException.class, () -> reduce((Iterable) null, (x, y) -> x, 0));
        assertThrows(IllegalArgumentException.class, () -> reduce(List.of(1, 2, 3), null, 0));
    }

    @Test
    @DisplayName("Iterable reduce() simple test")
    public void testReduceIterableSimple() {
        List<Integer> list = List.of(1, 2, 3);
        int expected = Stream.of(1, 2, 3).reduce(0, Math::addExact);
        int actual = reduce(list, Math::addExact, 0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Iterator reduce() precondition test")
    public void reduceIteratorTest() {
        assertThrows(IllegalArgumentException.class, () -> reduce((Iterator) null, (x, y) -> x, 0));
        assertThrows(IllegalArgumentException.class, () -> reduce(List.of(1, 2, 3).iterator(), null, 0));
    }

    @Test
    @DisplayName("Iterator reduce() simple test")
    public void testReduceIteratorSimple() {
        Iterator<Integer> iterator = List.of(1, 2, 3).iterator();
        int expected = Stream.of(1, 2, 3).reduce(0, Math::addExact);
        int actual = reduce(iterator, Math::addExact, 0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("equals() precondition test")
    public void equalsTest() {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        List<Integer> list3 = List.of(1, 2, 3);
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        Iterator<Integer> iterator3 = list3.iterator();

        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(null, iterator1));
        assertThrows(IllegalArgumentException.class, () -> Iterators.equals(iterator1, null));
        assertTrue(Iterators.equals(iterator1, iterator2));
        assertFalse(Iterators.equals(iterator1, iterator3));
    }

    static Stream<Arguments> equalsData() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3).iterator(), List.of(1, 2, 3).iterator()),
                Arguments.of(List.of(2, 3, 4, 5, 6).iterator(), List.of(2, 3, 4, 5, 6).iterator()),
                Arguments.of(List.of(1).iterator(), Stream.of(1).iterator()));
    }

    @DisplayName("equals() simple test")
    @ParameterizedTest
    @MethodSource("equalsData")
    void testEqualsSimple(Iterator<Integer> firstIterator, Iterator<Integer> secondIterator) {
        assertTrue(Iterators.equals(firstIterator, secondIterator));
    }

    @Test
    @DisplayName("equals() not match length test")
    public void testEqualsNotMatchLength() {
        Iterator<Integer> firstIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> secondIterator = Stream.iterate(1, x -> x + 1).limit(4).iterator();
        assertFalse(Iterators.equals(firstIterator, secondIterator));
    }

    @Test
    @DisplayName("equals() not match element test")
    public void testEqualsNotMatchElement() {
        Iterator<Integer> firstIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> secondIterator = Stream.iterate(4, x -> x + 1).limit(3).iterator();
        assertFalse(Iterators.equals(firstIterator, secondIterator));
    }

    @Test
    @DisplayName("equals() not match order test")
    public void testEqualsNotMatchOrder() {
        Iterator<Integer> firstIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> secondIterator = Stream.iterate(3, x -> x - 1).limit(3).iterator();
        assertFalse(Iterators.equals(firstIterator, secondIterator));
    }

    @Test
    @DisplayName("equals() empty test")
    public void testEqualsEmpty() {
        Iterator<Integer> firstIterator = new ArrayList<Integer>().iterator();
        Iterator<Integer> secondIterator = Collections.emptyIterator();
        assertTrue(Iterators.equals(firstIterator, secondIterator));
    }

    @Test
    @DisplayName("toString() precondition test")
    public void toStringTest() {
        List<String> list = List.of("jaehyeon", "is", "best");
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(null, " "));
        Iterator<Integer> integerIterator = List.of(1, 2, 3).iterator();
        assertThrows(IllegalArgumentException.class, () -> Iterators.toString(integerIterator, null));
    }

    static Stream<Arguments> toStringData() {
        return Stream.of(
                Arguments.of(List.of("jae", "is", "hyeon").iterator(), "jae is hyeon"),
                Arguments.of(List.of("hi").iterator(), "hi"));
    }

    @ParameterizedTest
    @MethodSource("toStringData")
    @DisplayName("toString() simple test")
    public void testToStringSimple(Iterator<String> iterator, String expected) {
        assertTrue(Iterators.toString(Stream.of(1).iterator(), "") instanceof String);
        Iterator<String> firstTargetIterator = List.of("jae", "is", "hyeon").iterator();
        String firstExpected = "jae is hyeon";
        String firstActual = Iterators.toString(firstTargetIterator, " ");

        Iterator<String> secondTargetIterator = List.of("jae", "is", "hyeon").iterator();
        String secondExpected = "jae,is,hyeon";
        String secondActual = Iterators.toString(secondTargetIterator, ",");

        assertEquals(firstExpected, firstActual);
        assertEquals(secondExpected, secondActual);

        assertEquals(Iterators.toString(iterator, " "), expected);
    }

    @Test
    @DisplayName("toString() element in null test")
    public void testToStringWithNull() {
        List<String> targetList = new ArrayList<>();
        targetList.add("jae");
        targetList.add(null);
        targetList.add("hyeon");

        Iterator<String> targetIterator = targetList.iterator();

        String expected = "jae|null|hyeon";
        String actual = Iterators.toString(targetIterator, "|");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toString() empty test")
    public void testToStringEmpty() {
        Iterator<String> targetIterator = Collections.emptyIterator();
        String expected = "";
        String actual = Iterators.toString(targetIterator, "|");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toString() fail test")
    public void testToStringNotMatch() {
        Iterator<String> firstTargetIterator = List.of("jae", "is", "hyeon").iterator();
        String firstExpected = "jae.is hyeon";
        String firstActual = Iterators.toString(firstTargetIterator, " ");

        assertNotEquals(firstExpected, firstActual);
    }

    @Test
    @DisplayName("map() precondition test")
    public void mapTest() {
        Iterator<Integer> integerIterator = List.of(1, 2, 3).iterator();
        assertThrows(IllegalArgumentException.class, () -> map(null, (x) -> x));
        assertThrows(IllegalArgumentException.class, () -> map(integerIterator, null));
    }

    @Test
    @DisplayName("map() simple test")
    public void testMapSimple() {
        assertTrue(Iterators.map(Stream.of(1).iterator(), (x) -> x) instanceof Iterator);
        Iterator<Integer> beforeMap = List.of(1, 2, 3).iterator();
        List<Integer> expected = List.of(2, 3, 4);

        assertEquals(toList(Iterators.map(beforeMap, (x) -> x + 1)), expected);
    }

    @Test
    @DisplayName("map() element typeCasting test")
    public void testMapTypeCasting() {
        Iterator<String> beforeMap = List.of("1", "2", "3").iterator();
        List<Integer> expected = List.of(1, 2, 3);

        assertEquals(toList(Iterators.map(beforeMap, ((x) -> Integer.parseInt(x)))), expected);
    }

    @Test
    @DisplayName("map() element empty test")
    public void testMapEmpty() {
        Iterator<Object> expected = Collections.emptyIterator();
        Iterator<Object> actual = map(List.of().iterator(), (x) -> x);
        assertTrue(Iterators.equals(expected, actual));
    }

    @Test
    @DisplayName("filter() precondition test")
    public void filterTest() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThrows(IllegalArgumentException.class, () -> filter(null, Objects::isNull));
        assertThrows(IllegalArgumentException.class, () -> filter(list.iterator(), null));
//TODO :옮기자
        Iterator<Integer> iterator = filter(list.iterator(), x -> x % 2 == 1);
        Iterator<Integer> result = Stream.iterate(1, x -> x + 1).limit(10).filter(x -> x % 2 == 1).iterator();
        assertTrue(Iterators.equals(iterator, result));

        assertTrue(filter(limit(iterate(1, x -> x + 1), 10), x -> x % 2 == 1) instanceof Iterator);
        assertTrue(Iterators.equals(filter(limit(iterate(1, x -> x + 1), 100), x -> x % 2 == 1),
                filter(Stream.iterate(1, x -> x + 1).limit(100).iterator(), x -> x % 2 == 1)));
    }

    @Test
    @DisplayName("filter() simple test")
    public void testFilterSimple() {
        assertTrue(filter(List.of(1, 2).iterator(), x -> x % 2 == 0) instanceof Iterator);
        Iterator<String> beforeFilter = List.of("jae", "hyeon").iterator();
        Iterator<String> afterFilter = filter(beforeFilter, Predicate.isEqual("hyeon"));
        List<String> expected = Collections.singletonList("hyeon");
        List<String> actual = toList(afterFilter);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("filter() element not match test")
    public void testFilterNoMatch() {
        Iterator<Integer> beforeFilter = List.of(1, 2, 3, 4, 5).iterator();
        Iterator<Integer> afterFilter = filter(beforeFilter, Predicate.isEqual(0));
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = toList(afterFilter);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("filter() element all match test")
    public void testFilterAllMatch() {
        Iterator<Double> beforeFilter = List.of(1.1, 2.3, 5.2, 0.1).iterator();
        Iterator<Double> afterFilter = filter(beforeFilter, x -> x > 0);
        List<Double> expected = List.of(1.1, 2.3, 5.2, 0.1);
        List<Double> actual = toList(afterFilter);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("filter() empty test")
    public void testFilterEmpty() {
        Iterator<Integer> beforeFilter = new ArrayList<Integer>().iterator();
        Iterator<Integer> afterFilter = filter(
                beforeFilter,
                new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        throw new AssertionFailedError("Should never be evaluated");
                    }
                });
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = toList(afterFilter);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("filter() hasNext => false then next() error")
    public void testFilterNoSuchException() {
        Iterator<Integer> beforeFilter = List.of(1, 2, 3).iterator();
        Iterator<Integer> afterFilter = filter(beforeFilter, x -> x < 0);
        assertThrows(NoSuchElementException.class, () -> afterFilter.next());

    }

    @Test
    @DisplayName("findFirst() precondition test")
    public void findFirstTest() {
        Iterator<Integer> iterator = Collections.singletonList(1).iterator();
        assertThrows(IllegalArgumentException.class, () -> findFirst(null, Objects::isNull));
        assertThrows(IllegalArgumentException.class, () -> findFirst(iterator, null));
    }

    @Test
    @DisplayName("findFirst() simple test")
    public void testFindFirstSimple() {
        Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();
        Integer expected = 2;
        Integer actual = findFirst(iterator, x -> x % 2 == 0);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findFirst() not find test")
    public void testFindFirstNotFound() {
        Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();
        assertEquals(null, findFirst(iterator, x -> x > 6));
    }

    @Test
    @DisplayName("findFirst() empty test")
    public void testFindFirstEmpty() {
        Iterator<Integer> iterator = Collections.emptyIterator();
        Integer actual = findFirst(
                iterator,
                new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        throw new AssertionFailedError("Should never be evaluated");
                    }
                }
        );
        assertEquals(null, actual);
    }

    @Test
    @DisplayName("iterate() precondition test")
    public void testIterate() {
        assertTrue(iterate(1, x -> x + 1) instanceof Iterator);
        assertThrows(IllegalArgumentException.class, () -> iterate(1, null));
    }

    @Test
    @DisplayName("iterate() test")
    public void iterateTest() {
        assertTrue(iterate(1, x -> x + 1) instanceof Iterator);
        assertTrue((limit(iterate(1, x -> x + 1), 10) instanceof Iterator));
        assertTrue(!Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(5).iterator()));
        assertTrue(Iterators.equals(limit(iterate(1, x -> x + 1), 10),
                Stream.iterate(1, x -> x + 1).limit(10).iterator()));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), " "),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf)
                        .reduce((x, y) -> x + " " + y).orElse(""));
        assertEquals(Iterators.toString(limit(iterate(1, x -> x + 1), 10), ","),
                Stream.iterate(1, x -> x + 1).limit(10).map(String::valueOf)
                        .collect(Collectors.joining(",")));
    }

    @Test
    @DisplayName("limit() precondition test")
    public void limitTest() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        long maxSize = 3;

        assertTrue(limit(list.iterator(), maxSize) instanceof Iterator);
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(null, maxSize));
        assertThrows(IllegalArgumentException.class, () -> Iterators.limit(list.iterator(), -1));
    }

    @Test
    @DisplayName("limit() simple test")
    public void testLimitSimple() {
        Iterator<Integer> beforeLimitIterator = List.of(1, 2, 3, 4, 5).iterator();
        Iterator<Integer> afterLimitIterator = limit(beforeLimitIterator, 3);
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = toList(afterLimitIterator);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("limit() hasNext()=> false then next() error")
    public void testLimitNoSuchException() {
        Iterator<Integer> beforeLimitIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> afterLimitIterator = limit(beforeLimitIterator, 0);

        assertThrows(NoSuchElementException.class, () -> afterLimitIterator.next());
    }

    @Test
    @DisplayName("limit() msxSize is iteratorSize over test")
    public void testOverMaxSizeIteratorSize() {
        Iterator<Integer> beforeLimitIterator = List.of(1, 2, 3).iterator();
        long maxSize = 5;
        assertEquals(count(limit(beforeLimitIterator, maxSize)), 3);
    }

    @Test
    @DisplayName("limit() empty test")
    public void testLimitResultEmpty() {
        Iterator<Integer> beforeLimitIterator = List.of(1, 2, 3, 4, 5).iterator();
        Iterator<Integer> afterLimitIterator = limit(beforeLimitIterator, 0);
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = toList(afterLimitIterator);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generate() precondition test")
    public void generateTest() {
        assertTrue(generate(() -> Integer.MAX_VALUE) instanceof Iterator);
        assertThrows(IllegalArgumentException.class, () -> generate(null));
    }

    @Test
    @DisplayName("generate() simple test")
    public void testGenerateSimple() {
        Object expected = null;
        Object actual = limit(generate(() -> null), 1).next();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("zip() precondition test")
    public void zipTest() {
        Iterator<Integer> iterator1 = List.of(1, 2, 3).iterator();
        Iterator<Integer> iterator2 = List.of(4, 2, 3).iterator();

        assertTrue(zip(Integer::equals, iterator1, iterator2) instanceof Iterator);
        assertThrows(IllegalArgumentException.class, () -> zip(null, iterator1, iterator2));
        assertThrows(IllegalArgumentException.class, () -> zip(Integer::equals, null, iterator2));
        assertThrows(IllegalArgumentException.class, () -> zip(Integer::equals, iterator1, null));
    }

    @Test
    @DisplayName("zip() simple test")
    public void testZipSimple() {
        Iterator<Integer> firstIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> secondIterator = List.of(4, 2, 3).iterator();

        Iterator<Boolean> expected = List.of(false, true, true).iterator();
        Iterator<Boolean> actual = zip(Integer::equals, firstIterator, secondIterator);

        assertTrue(Iterators.equals(expected, actual));
    }

    @Test
    @DisplayName("zip() iterator size different test")
    public void testIteratorSizeDifferent() {
        Iterator<Integer> firstIterator = List.of(1, 2, 3).iterator();
        Iterator<Integer> secondIterator = Stream.iterate(1, x -> x + 1).limit(10).iterator();

        Iterator<Integer> expected = List.of(2, 4, 6).iterator();
        Iterator<Integer> actual = zip(Integer::sum, firstIterator, secondIterator);

        assertTrue(Iterators.equals(expected, actual));

    }


    @Test
    @DisplayName("zip() empty test")
    public void testZipEmpty() {
        Iterator<Integer> firstIterator = Collections.emptyIterator();
        Iterator<Integer> secondIterator = Collections.emptyIterator();
        Iterator<Boolean> expected = Collections.emptyIterator();
        Iterator<Boolean> actual = zip(Integer::equals, firstIterator, secondIterator);

        assertTrue(Iterators.equals(expected, actual));
    }

    @Test
    @DisplayName("zip() hasNext() => false then next() error")
    public void testZipNoSuchException() {
        Iterator<Integer> firstIterator = Collections.emptyIterator();
        Iterator<Integer> secondIterator = Collections.emptyIterator();
        Iterator<Integer> zipIterator = zip(Integer::sum, firstIterator, secondIterator);
        assertThrows(NoSuchElementException.class, () -> zipIterator.next());
    }

    @Test
    @DisplayName("count() precondition test")
    public void countTest() {
        assertThrows(IllegalArgumentException.class, () -> count(null));
        assertThrows(ArithmeticException.class, () -> count(Stream.iterate(1, x -> x + 1).iterator()));
    }

    @Test
    @DisplayName("count() simple test")
    public void testCountSimple() {
        assertEquals(count(Stream.iterate(1, x -> x + 1).limit(10).iterator()), 10L);
    }

    @Test
    @DisplayName("count() empty test")
    public void testCountEmpty() {
        Iterator<Integer> targetIterator = Collections.emptyIterator();
        long expected = 0;
        long actual = count(targetIterator);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("count() after iterator.next test")
    public void testCountNextAfter() {
        Iterator<Integer> targetIterator = List.of(1, 2).iterator();
        targetIterator.next();

        long expected = 1;
        long actual = count(targetIterator);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("get() precondition test")
    public void getTest() {
        Iterator<Integer> integerIterator = List.of(1).iterator();
        assertTrue(get(integerIterator, 1L) instanceof Integer);
        Iterator<String> stringIterator = List.of("a").iterator();
        assertTrue(get(stringIterator, 1L) instanceof String);

        assertThrows(IndexOutOfBoundsException.class, () -> get(integerIterator, -1L));
        assertThrows(IllegalArgumentException.class, () -> get(null, 0L));
    }

    @Test
    @DisplayName("get() simple test")
    public void testGetSimple() {
        Iterator<Integer> integerIterator = List.of(1, 2, 3).iterator();
        Integer expected = 1;
        Integer actual = get(integerIterator, 0L);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("get() empty test")
    public void testGetEmpty() {
        Iterator<Integer> integerIterator = Collections.emptyIterator();
        assertThrows(NoSuchElementException.class, () -> get(integerIterator, 0L));
    }

    @Test
    @DisplayName("toList() precondition test")
    public void toListTest() {
        Iterator<Integer> integerIterator = List.of(1).iterator();
        assertTrue(toList(integerIterator) instanceof List);
        assertThrows(IllegalArgumentException.class, () -> toList(null));
    }

    @Test
    @DisplayName("toList() simple test")
    public void testToListSimple() {
        Iterator<Integer> integerIterator = List.of(1, 2).iterator();
        List<Integer> expected = List.of(1, 2);
        List<Integer> actual = toList(integerIterator);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toList() empty  test")
    public void testToListEmpty() {
        Iterator<Integer> integerIterator = Collections.emptyIterator();
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = toList(integerIterator);

        assertEquals(expected, actual);
    }

}
